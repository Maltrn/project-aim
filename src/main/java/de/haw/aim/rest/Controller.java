package de.haw.aim.rest;

import de.haw.aim.authentication.AuthenticationCompoment;
import de.haw.aim.authentication.persistence.User;
import de.haw.aim.rest.dto.InfoDTO;
import de.haw.aim.rest.dto.LoginRequest;
import de.haw.aim.rest.dto.LoginResponse;
import de.haw.aim.rest.dto.UserDTO;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import de.haw.aim.vendor.VendorComponent;
import de.haw.aim.vendor.persistence.Vendor;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class Controller implements FileApi, LoginApi, ProductApi, VendorApi{

    @Autowired
    AuthenticationCompoment authenticationCompoment;

    @Autowired
    VendorComponent vendorComponent;

    @Override
    public ResponseEntity<List<InfoDTO>> vendorGet() {
        return null;
    }

    @Override
    public ResponseEntity<InfoDTO> vendorIdGet(@ApiParam(value = "ID des Anbieters dessen Anbieterinformationen abgefragt werden sollen", required = true) @PathVariable("id") String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> vendorPut(@ApiParam(value = "aktualisiertes oder neues Anbieterinfo Objekt", required = true) @RequestBody InfoDTO infodto) throws ValueDoesntValidateToConfigFileException {
        infodto.validate();
        return null;
    }

    @Override
    public ResponseEntity<List<Info>> productGet() {
        return null;
    }

    @Override
    public ResponseEntity<Info> productIdGet(@ApiParam(value = "ID des Produktes dessen Produktinformationen abgefragt werden sollen", required = true) @PathVariable("id") String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> productIdPut(@ApiParam(value = "ID des Produktes dessen Produktinformationen aktualisiert werden sollen", required = true) @PathVariable("id") String id, @ApiParam(value = "aktualisiertes oder neues Produktinfo Objekt", required = true) @RequestBody Info body) {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> fileGet() {
        return null;
    }

    @Override
    public ResponseEntity<Void> fileIdGet(@ApiParam(value = "ID der Datei welche aberufen werden soll", required = true) @PathVariable("id") String id) {
        return null;
    }

    @Override
    public ResponseEntity<String> fileIdPut(@ApiParam(value = "ID der Datei welche überschrieben werden soll", required = true) @PathVariable("id") String id, @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<String> filePut(@ApiParam(value = "file detail") @RequestPart("file") MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> loginPost(@ApiParam(value = "Username und Passwort", required = true) @RequestBody LoginRequest loginRequest) {
        // try to get user based on username and password
        User user = authenticationCompoment.login(loginRequest.getUsername(),loginRequest.getPassword());
        // if user is null do some error handling
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // find vendor for user
        Vendor usersVendor = vendorComponent.getVendor(user);

        // if vendor is null do some error handling
        if(usersVendor == null){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        // prepare LoginResponse for UserDTO based on user
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setCurrentToken(user.getCurrentToken());
        loginResponse.setUsername(user.getUsername());

        // prepare UserDTO for ResponseEntity
        UserDTO userDto = new UserDTO(loginResponse, usersVendor.getVendorInfoId(), usersVendor.getProdcutInfoIds());

        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
