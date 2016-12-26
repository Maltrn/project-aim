package de.haw.aim.rest;

import de.haw.aim.authentication.facade.AuthenticationInterface;
import de.haw.aim.authentication.persistence.User;
import de.haw.aim.importer.DataImporter;
import de.haw.aim.rest.dto.InfoDTO;
import de.haw.aim.rest.dto.LoginRequest;
import de.haw.aim.rest.dto.LoginResponse;
import de.haw.aim.rest.dto.UserDTO;
import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import de.haw.aim.vendor.facade.IVendor;
import de.haw.aim.vendor.persistence.ProductInfo;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorInfo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.ServiceUnavailableException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@CrossOrigin
@RestController
public class Controller implements FileApi, LoginApi, ProductApi, VendorApi {

    @Autowired
    private DataImporter dataImporter;

    @Autowired
    private AuthenticationInterface authComponent;

    @Autowired
    private IVendor iVendor;

    @Autowired
    private IUploadCenter iUploadCenter;

    @Value("${uploadcenter.fileslocation}")
    private String fileLocationFolder;

    private final Logger log = LoggerFactory.getLogger(Controller.class);

    @Override
    public ResponseEntity<List<InfoDTO>> vendorGet() {
        List<InfoDTO> retVal = new ArrayList<>();

        for (Vendor v : iVendor.getVendors()) {
            retVal.add(InfoDTO.from(v.getVendorInfo()));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InfoDTO> vendorIdGet(
            @ApiParam(value = "ID des Anbieters dessen Anbieterinformationen abgefragt werden", required = true)
            @PathVariable("id") String id) {
        Vendor retVal = iVendor.getVendor(id);
        if (retVal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(InfoDTO.from(retVal.getVendorInfo()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> vendorPut(
            @ApiParam(value = "aktualisiertes oder neues Anbieterinfo Objekt", required = true)
            @RequestBody InfoDTO infodto,
            @RequestHeader("Authorization") String headerToken)
            throws ValueDoesntValidateToConfigFileException {
        // fetch token from header for user lookup
        String token = headerToken.substring("TOKEN".length()).trim();

        // find user by token provided from header
        User currentUser = authComponent.findByToken(token);

        // if no user found for token return Bad Request
        if (currentUser == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // otherwise get Vendor for User to update vendor info
        Vendor vendor = iVendor.getVendor(currentUser);

        // check if InfoDTO is valid
        infodto.validate();

        // set iUploadCenter for infoDTO to avoid autowiring null pointer
        infodto.setiUploadCenter(iUploadCenter);

        // if valid save entity in DB
        VendorInfo vendorInfo = infodto.convertToVendorInfo();

        // set ID to actual vendor ID
        vendorInfo.setId(vendor.getId());
        if (iVendor.putVendor(vendorInfo) != null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<InfoDTO>> productGet() {
        List<InfoDTO> retVal = new ArrayList<>();

        for (ProductInfo p : iVendor.getProducts()) {
            retVal.add(InfoDTO.from(p));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InfoDTO> productIdGet(
            @ApiParam(value = "ID des Produktes dessen Produktinformationen abgefragt werden", required = true)
            @PathVariable("id") String id) {
        ProductInfo retVal = iVendor.getProduct(id);
        if (retVal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(InfoDTO.from(retVal), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> productIdPut(
            @ApiParam(value = "ID des Produktes dessen Produktinformationen aktualisiert werden", required = true)
            @PathVariable("id") String id,
            @ApiParam(value = "aktualisiertes oder neues Produktinfo Objekt", required = true)
            @RequestBody InfoDTO infoDTO,
            @RequestHeader("Authorization") String headerToken)
            throws ValueDoesntValidateToConfigFileException {
        // fetch token from header for user lookup
        String token = headerToken.substring("TOKEN".length()).trim();

        // find user by token provided from header
        User currentUser = authComponent.findByToken(token);

        // if no user found for token return Bad Request
        if (currentUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // after that check infoDTO
        infoDTO.validate();

        // set iUploadCenter for infoDTO to avoid autowiring null pointer
        infoDTO.setiUploadCenter(iUploadCenter);

        // convert to productInfo Entity
        ProductInfo productInfo = infoDTO.convertToProductInfo();

        // get Vendor for User to update product info
        Vendor vendor = iVendor.getVendor(currentUser);

        vendor.putProductInfo(productInfo);

        iVendor.saveVendor(vendor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // FIXME think about namespace maybe "GET /fileIdList"
    @Override
    public ResponseEntity<List<String>> fileGet(@RequestHeader("Authorization") String headerToken) {
        // fetch token from header for user lookup
        String token = headerToken.substring("TOKEN".length()).trim();

        // find user by token provided from header
        User currentUser = authComponent.findByToken(token);

        // if no user found for token return Bad Request
        if (currentUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // Otherwise we can get the Vendor for the user
        Vendor vendor = iVendor.getVendor(currentUser);

        List<String> retVal = vendor.getFiles().stream().map(UploadedFile::getId).collect(Collectors.toList());

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> fileIdGet(
            @ApiParam(value = "ID der Datei welche aberufen werden soll", required = true)
            @PathVariable("id") String id) {
        // If the given ID does not exist please tell that the user
        if (!iUploadCenter.checkForExistence(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // otherwise get our UploadedFile Entity
        String location = fileLocationFolder + File.separator + iUploadCenter.findById(id).getLocation();

        // Create a file from its path
        File file = new File(location);
        byte[] fileResponse;
        //String contentType;
        try {
            InputStream targetStream = new FileInputStream(file);
            fileResponse = IOUtils.toByteArray(targetStream);
            //contentType = Files.probeContentType(Paths.get(file.getPath()));
        } catch (FileNotFoundException e) {
            this.log.error("file not found", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            this.log.error("io exception", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_PDF);

        // Back to the frontend
        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> fileIdPut(
            @ApiParam(value = "ID der Datei welche überschrieben werden soll", required = true)
            @PathVariable("id") String id, @ApiParam(value = "file detail")
            @RequestPart("file") MultipartFile file,
            @RequestHeader("Authorization") String headerToken) {
        // fetch token from header for user lookup
        String token = headerToken.substring("TOKEN".length()).trim();

        // find user by token provided from header
        User currentUser = authComponent.findByToken(token);

        // if no user found for token return Bad Request
        if (currentUser == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // Let the UploadCenter replace the file
        try {
            iUploadCenter.replaceFile(id, file);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> filePut(
            @ApiParam(value = "file detail")
            @RequestPart("file") MultipartFile file,
            @RequestHeader("Authorization") String headerToken) {
        // fetch token from header for user lookup
        String token = headerToken.substring("TOKEN".length()).trim();

        // find user by token provided from header
        User currentUser = authComponent.findByToken(token);

        // if no user found for token return Bad Request
        if (currentUser == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // Otherwise we can get the Vendor for the user
        Vendor vendor = iVendor.getVendor(currentUser);

        // Now we can try to create an uploadedFile
        UploadedFile uploadedFile;

        try {
            uploadedFile = iUploadCenter.uploadFile(file, vendor.getId());
        } catch (IOException e) {
            // If the UploadedFile creation fails, we can assume the file is invalid
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Otherwise we have a proper file to add to the vendor FileList
        vendor.addFile(uploadedFile);

        // Save the Vendor Entity
        iVendor.saveVendor(vendor);

        return new ResponseEntity<>(uploadedFile.getId(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> loginPost(
            @ApiParam(value = "Username und Passwort", required = true)
            @RequestBody LoginRequest loginRequest) {
        System.out.println("inside login: " + loginRequest.getUsername() + " pw: " + loginRequest.getPassword());
        // try to get user based on username and password
        User user = authComponent.login(loginRequest.getUsername(), loginRequest.getPassword());
        // if user is null do some error handling
        if (user == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        // find vendor for user
        Vendor usersVendor = iVendor.getVendor(user);

        // if vendor is null do some error handling
        if (usersVendor == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        // Try to synchronize the DB from Symphony, if this fails we signal the Service is not available
        try {
            dataImporter.synchronize();
        } catch (ServiceUnavailableException e) {
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
    public ResponseEntity<String> invalidValue(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
