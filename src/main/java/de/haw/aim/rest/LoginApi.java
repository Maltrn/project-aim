package de.haw.aim.rest;

import de.haw.aim.rest.dto.LoginRequest;
import de.haw.aim.rest.dto.UserDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

@Api(value = "login", description = "the login API")
public interface LoginApi {

    @ApiOperation(value = "", notes = "Meldet den Benutzer (spezifiziert durch Username und Passwort) am System an und liefert einen LoginResponse zurück, der für weitere API Funktionen benötigt wird. Außerdem enthält die Response die ID des Anbieter der dem Benutzer zugeordnet ist und alle dessen Produkt IDs.", response = UserDTO.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "LoginResponse, Anbieterinfo ID und alle Produktinfo IDs des Anbieters", response = UserDTO.class),
        @ApiResponse(code = 401, message = "Benutzername existiert nicht oder angegebenen Passwort ist falsch", response = UserDTO.class),
        @ApiResponse(code = 503, message = "Login nicht erfolgreich, da keine Anbieter und/oder Produktinformationen abgerufen werden können.", response = UserDTO.class) })
    @RequestMapping(value = "/login",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<UserDTO> loginPost(

            @ApiParam(value = "Username und Passwort", required = true) @RequestBody LoginRequest loginRequest

    );

}
