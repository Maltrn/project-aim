package de.haw.aim.rest;

import de.haw.aim.authentication.AuthenticationInterface;
import de.haw.aim.authentication.persistence.User;
import de.haw.aim.rest.dto.InfoDTO;
import de.haw.aim.rest.dto.LoginRequest;
import de.haw.aim.rest.dto.LoginResponse;
import de.haw.aim.rest.dto.UserDTO;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import de.haw.aim.vendor.facade.IVendor;
import de.haw.aim.vendor.persistence.ProductInfo;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller implements FileApi, LoginApi, ProductApi, VendorApi
{

    @Autowired
    AuthenticationInterface authenticationCompoment;

    @Autowired
    IVendor vendorComponent;

    @Override
    public ResponseEntity<List<InfoDTO>> vendorGet()
    {
        List<InfoDTO> retVal = vendorComponent.getVendors().stream().map(v -> InfoDTO.from(v.getVendorInfo())).collect(Collectors.toList());
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InfoDTO> vendorIdGet(@ApiParam(value = "ID des Anbieters dessen Anbieterinformationen abgefragt werden sollen", required = true) @PathVariable("id") String id)
    {
        Vendor retVal = vendorComponent.getVendor(id);
        if(retVal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(InfoDTO.from(retVal.getVendorInfo()),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> vendorPut(@ApiParam(value = "aktualisiertes oder neues Anbieterinfo Objekt", required = true) @RequestBody InfoDTO infodto, @RequestHeader("Authorization") String headerToken) throws ValueDoesntValidateToConfigFileException
    {
        // fetch token from header for user lookup
        String token = headerToken.substring("TOKEN".length()).trim();

        // find user by token provided from header
        User currentUser = authenticationCompoment.findByToken(token);

        // if no user found for token return Bad Request
        if(currentUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // otherwise get Vendor for User to update vendor info
        Vendor vendor = vendorComponent.getVendor(currentUser);

        // check if InfoDTO is valid
        infodto.validate();
        // if valid save entity in DB
        VendorInfo vendorInfo = infodto.convertToVendorInfo();

        // set ID to actual vendor ID
        vendorInfo.setId(vendor.getId());
        if(vendorComponent.putVendor(vendorInfo))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<InfoDTO>> productGet()
    {
        List<InfoDTO> retVal = vendorComponent.getProducts().stream().map(InfoDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InfoDTO> productIdGet(@ApiParam(value = "ID des Produktes dessen Produktinformationen abgefragt werden sollen", required = true) @PathVariable("id") String id)
    {
        ProductInfo retVal = vendorComponent.getProduct(id);
        if(retVal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(InfoDTO.from(retVal),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> productIdPut(@ApiParam(value = "ID des Produktes dessen Produktinformationen aktualisiert werden sollen", required = true) @PathVariable("id") String id, @ApiParam(value = "aktualisiertes oder neues Produktinfo Objekt", required = true) @RequestBody InfoDTO infoDTO) throws ValueDoesntValidateToConfigFileException
    {
        // check if InfoDTO is valid
        infoDTO.validate();
        return null;
    }

    @Override
    public ResponseEntity<List<String>> fileGet()
    {
        return null;
    }

    @Override
    public ResponseEntity<Void> fileIdGet(@ApiParam(value = "ID der Datei welche aberufen werden soll", required = true) @PathVariable("id") String id)
    {
        return null;
    }

    @Override
    public ResponseEntity<String> fileIdPut(@ApiParam(value = "ID der Datei welche überschrieben werden soll", required = true) @PathVariable("id") String id, @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file)
    {
        return null;
    }

    @Override
    public ResponseEntity<String> filePut(@ApiParam(value = "file detail") @RequestPart("file") MultipartFile file)
    {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> loginPost(@ApiParam(value = "Username und Passwort", required = true) @RequestBody LoginRequest loginRequest)
    {
        // try from get user based on username and password
        User user = authenticationCompoment.login(loginRequest.getUsername(), loginRequest.getPassword());
        // if user is null do some error handling
        if (user == null)
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // find vendor for user
        Vendor usersVendor = vendorComponent.getVendor(user);

        // if vendor is null do some error handling
        if (usersVendor == null)
        {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        // prepare LoginResponse for UserDTO based on user
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setCurrentToken(user.getCurrentToken());
        loginResponse.setUsername(user.getUsername());

        // prepare UserDTO for ResponseEntity
        UserDTO userDto = new UserDTO(loginResponse, usersVendor.getVendorInfoId(), usersVendor.getProdcutInfoIds());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @ExceptionHandler(ValueDoesntValidateToConfigFileException.class)
    public ResponseEntity<String> invalidValue(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
