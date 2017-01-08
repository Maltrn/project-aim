package de.haw.aim.rest;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

@Api(value = "file", description = "the file API")
public interface FileApi
{

    @ApiOperation(value = "", notes = "Liefert alle file IDs des Anbieters", response = String.class, responseContainer = "List", authorizations = {
            @Authorization(value = "user.json")}, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste aller file IDs die dem Anbieter zugeordnet sind", response = String.class),
            @ApiResponse(code = 401, message = "Der Token ist ungültig", response = String.class)})
    @RequestMapping(value = "/file",produces = {"application/json"},method = RequestMethod.GET)
    ResponseEntity<List<String>> fileGet(@RequestHeader("Authorization") String headerToken);


    @ApiOperation(value = "", notes = "Liefert eine bestimmte Datei", response = Void.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "angeforderte Datei", response = Void.class),
            @ApiResponse(code = 401, message = "Der Token ist ungültig", response = Void.class),
            @ApiResponse(code = 404, message = "Es wurde keine Datei mit der angegebenen ObjectId gefunden", response = Void.class)})
    @RequestMapping(value = "/file/{id}",produces = {"image/jpeg", "image/png", "image/gif", "application/pdf"},method = RequestMethod.GET)
    ResponseEntity<byte[]> fileIdGet(@ApiParam(value = "ID der Datei, welche aberufen werden soll", required = true) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "Überschreibt eine vorhandene Datei. Produkt- und Anbieterinfos die die ursprüngliche Datei verwendet haben, zeigen nach diesem Aufruf auf die neue Datei", response = String.class, authorizations = {
            @Authorization(value = "user.json")}, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Die Datei wurde erfolgreich hochgeladen und die bestehende Datei überschrieben", response = String.class),
            @ApiResponse(code = 400, message = "Ungültiger Dateityp, Dateigröße", response = String.class),
            @ApiResponse(code = 401, message = "Der Token ist ungültig", response = Void.class)})
    @RequestMapping(value = "/file/{id}",consumes = {"application/x-www-form-urlencoded"},method = RequestMethod.PUT)
    ResponseEntity<Void> fileIdPut(
            @ApiParam(value = "ID der Datei welche überschrieben werden soll", required = true) @PathVariable("id") String id,
            @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file,
            @RequestHeader("Authorization") String headerToken);


    @ApiOperation(value = "", notes = "Lädt eine neue Datei hoch", response = String.class, authorizations = {
            @Authorization(value = "user.json")}, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Die Datei wurde erfolgreich hochgeladen", response = String.class),
            @ApiResponse(code = 400, message = "Ungültiger Dateityp oder Dateigröße", response = String.class),
            @ApiResponse(code = 401, message = "Der Token ist ungültig", response = Void.class)})
    @RequestMapping(value = "/file",consumes = {"multipart/form-data"},method = RequestMethod.PUT)
    ResponseEntity<String> filePut(@ApiParam(value = "file detail") @RequestPart("file") MultipartFile file,@RequestHeader("Authorization") String headerToken);


    @ApiOperation(value = "", notes = "Löscht eine bestimmte Datei", response = Void.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Datei erfolgreich gelöscht", response = Void.class),
            @ApiResponse(code = 401, message = "Der Token ist ungültig", response = Void.class),
            @ApiResponse(code = 404, message = "Es wurde keine Datei mit der angegebenen ObjectId gefunden", response = Void.class)})
    @RequestMapping(value = "/file/{id}",method = RequestMethod.DELETE)
    ResponseEntity<Void> fileIdDelete(@ApiParam(value = "ID der Datei welche gelöscht werden soll", required = true) @PathVariable("id") String id);
}
