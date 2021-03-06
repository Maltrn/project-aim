package de.haw.aim.rest;

import de.haw.aim.rest.dto.InfoDTO;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

@Api(value = "product", description = "the product API")
public interface ProductApi
{

    @ApiOperation(value = "", notes = "Liefert die Produktinformationen aller Produkte aller Anbieter", response = InfoDTO.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Liste aller Produktinfo Objekte", response = InfoDTO.class)})
    @RequestMapping(value = "/product",produces = {"application/json"},method = RequestMethod.GET)
    ResponseEntity<List<InfoDTO>> productGet();


    @ApiOperation(value = "", notes = "Liefert die Produktinformationen eines bestimmten Produktes", response = InfoDTO.class, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "angefordertes Produktinfo Objekt", response = InfoDTO.class),
        @ApiResponse(code = 404, message = "Es wurde kein Produkt mit der angegebenen ObjectId gefunden", response = InfoDTO.class)})
    @RequestMapping(value = "/product/{id}",produces = {"application/json"},method = RequestMethod.GET)
    ResponseEntity<InfoDTO> productIdGet(
        @ApiParam(value = "ID des Produktes dessen Produktinformationen abgefragt werden sollen", required = true) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "Aktualisiert eine vorhandene Produktinformation. Neue Produkte können nicht erstellt werden", response = Void.class, authorizations = {
        @Authorization(value = "user.json")}, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Die vorhandene Produktinformationen wurde aktualisiert.", response = InfoDTO.class),
        @ApiResponse(code = 400, message = "Die Produktinformationen konnte nicht aktuallisert werden.", response = Void.class),
        @ApiResponse(code = 401, message = "Der Token ist ungültig", response = Void.class)})
    @RequestMapping(value = "/product/{id}", consumes = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<?> productIdPut(
            @ApiParam(value = "ID des Produktes dessen Produktinformationen aktualisiert werden sollen", required = true)
        @PathVariable("id") String id,
            @ApiParam(value = "aktualisiertes oder neues Produktinfo Objekt", required = true) @RequestBody InfoDTO infoDTO,
            @RequestHeader("Authorization") String headerToken) throws ValueDoesntValidateToConfigFileException;

}
