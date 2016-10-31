package de.haw.aim.rest;

import io.swagger.model.Info;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

@Api(value = "vendor", description = "the vendor API")
public interface VendorApi {

    @ApiOperation(value = "", notes = "Liefert die Anbieterinformationen aller Anbieter", response = Info.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Liste aller Anbieterinfo Objekte", response = Info.class) })
    @RequestMapping(value = "/vendor",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Info>> vendorGet();


    @ApiOperation(value = "", notes = "Liefert die Anbieterinformationen eines bestimmten Anbieters", response = Info.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "angefordertes Anbieterinfo Objekt", response = Info.class),
        @ApiResponse(code = 404, message = "Es wurde kein Anbieter mit der angegebenen ObjectId gefunden", response = Info.class) })
    @RequestMapping(value = "/vendor/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Info> vendorIdGet(
            @ApiParam(value = "ID des Anbieters dessen Anbieterinformationen abgefragt werden sollen", required = true) @PathVariable("id") String id


    );


    @ApiOperation(value = "", notes = "Erstellt eine neue Anbieterinformation oder aktualisiert die vorhandene. Es wird die Anbieterinformation bearbeitet die dem Benutzer zugewiesen ist. Das property id kann einen beliebigen Wert haben und wird nicht ausgewertet.", response = Void.class, authorizations = {
        @Authorization(value = "user")
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Die vorhandene Anbieterinformation wurde aktualisiert.", response = Void.class),
        @ApiResponse(code = 400, message = "Die Anbieterinformation konnte nicht aktuallisert werden.", response = Void.class),
        @ApiResponse(code = 401, message = "Der Token ist ungültig", response = Void.class) })
    @RequestMapping(value = "/vendor",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> vendorPut(

            @ApiParam(value = "aktualisiertes oder neues Anbieterinfo Objekt", required = true) @RequestBody Info body

    );

}
