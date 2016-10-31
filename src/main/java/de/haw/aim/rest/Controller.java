package de.haw.aim.rest;

import de.haw.aim.rest.dto.LoginRequest;
import de.haw.aim.rest.dto.UserDTO;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Rene on 31.10.2016.
 */
public class Controller implements FileApi, LoginApi, ProductApi, VendorApi{
    @Override
    public ResponseEntity<List<Info>> vendorGet() {
        return null;
    }

    @Override
    public ResponseEntity<Info> vendorIdGet(@ApiParam(value = "ID des Anbieters dessen Anbieterinformationen abgefragt werden sollen", required = true) @PathVariable("id") String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> vendorPut(@ApiParam(value = "aktualisiertes oder neues Anbieterinfo Objekt", required = true) @RequestBody Info body) {
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
        return null;
    }
}
