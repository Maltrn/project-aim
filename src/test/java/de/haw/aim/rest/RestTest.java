package de.haw.aim.rest;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import de.haw.aim.vendor.persistence.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.StringContains.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private VendorInfoRepository vendorInfoRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${local.server.port}")
    private int port;

    private String productName = "productName";
    private String shortDescription = "short Description";
    private String longDescription = "long description";

    @BeforeMethod
    public void setUp() throws Exception
    {
        Picture picture = new Picture("testPath");
        List<UploadedFile> fileGallery = new ArrayList<>();
        fileGallery.add(picture);

        Fact fact = new Fact("key", "value");
        List<Fact> facts = new ArrayList<>();
        facts.add(fact);

        VendorInfo vendorInfo = new VendorInfo("Vendor", shortDescription, longDescription, picture, fileGallery, facts);

        ProductInfo productInfo = new ProductInfo(productName, shortDescription, longDescription, picture, fileGallery, facts);
        List<ProductInfo> productInfos = new ArrayList<>();
        productInfos.add(productInfo);

        User user = new User("userName", "password");
        user.setCurrentToken("handsomeTOKEN");
        List<User> users = new ArrayList<>();
        users.add(user);

        Vendor testVendor = new Vendor(vendorInfo, productInfos, users, fileGallery);

        pictureRepository.deleteAll();
        userRepository.deleteAll();
        vendorInfoRepository.deleteAll();
        productInfoRepository.deleteAll();
        vendorRepository.deleteAll();

        pictureRepository.save(picture);
        vendorInfoRepository.save(vendorInfo);
        productInfoRepository.save(productInfo);
        userRepository.save(user);
        testVendor.setVendorInfo(vendorInfoRepository.findAll().get(0));
        vendorRepository.save(testVendor);

        RestAssured.basePath = "/api";
        RestAssured.port = this.port;
    }

    @Test
    public void testVendorGet() throws Exception
    {
        String response = when()
                .get("/vendor")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK).extract().response().asString();

        Assert.assertEquals(this.cleanString(response), "[" + this.cleanString(
                     this.vendorJson(
                        vendorRepository.findAll().get(0).getId(),
                        shortDescription,
                        longDescription,
                        pictureRepository.findAll().get(0).getId(),
                        pictureRepository.findAll().get(0).getId(),
                        "\"key\" : \"value\"")) + "]");
    }

    @Test
    public void testVendorIdGet() throws Exception
    {
        String response = when()
                .get("/vendor/" + this.vendorRepository.findAll().get(0).getId())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK).extract().response().asString();

        Assert.assertEquals(
                this.cleanString(response),
                this.cleanString(this.vendorJson(
                        vendorRepository.findAll().get(0).getId(),
                        shortDescription,
                        longDescription,
                        pictureRepository.findAll().get(0).getId(),
                        pictureRepository.findAll().get(0).getId(),
                        "\"key\" : \"value\"") ));
    }

    @Test
    public void testVendorPut() throws Exception
    {
        String id = vendorRepository.findAll().get(0).getId();
        String jsonBody = this.cleanString(this.vendorJson(
                id,
                shortDescription,
                longDescription,
                pictureRepository.findAll().get(0).getId(),
                pictureRepository.findAll().get(0).getId(),
                "\"key\" : \"value221\""));

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "TOKEN handsomeTOKEN")
                .body(jsonBody)
                .when()
                .put("/vendor")
                .then()
                .statusCode(HttpStatus.SC_OK);

        VendorInfo vendorInfo = this.vendorInfoRepository.findOne(id);
        Assert.assertEquals(vendorInfo.getShortDescription(), "shortDescription");
        Assert.assertEquals(vendorInfo.getLongDescription(), "longdescription");
        Assert.assertEquals(vendorInfo.getFacts().get(0).getValue(), "value221");
    }

    @Test
    public void productGetTest()
    {
        given().when().get("/product").then().statusCode(HttpStatus.SC_OK).body(containsString(productName));
        given().when().get("/product").then().statusCode(HttpStatus.SC_OK).body(containsString(shortDescription));
        given().when().get("/product").then().statusCode(HttpStatus.SC_OK).body(containsString(longDescription));
    }

    private String vendorJson(String id, String shortDescription, String longDescription, String mainPic, String fileGallery, String facts)
    {
        return "{\n" +
                "  \"id\" : \"" + id + "\",\n" +
                "  \"name\" : \"Vendor\",\n" +
                "  \"shortDescription\" : \"" + shortDescription + "\",\n" +
                "  \"longDescription\" : \"" + longDescription + "\",\n" +
                "  \"mainPic\" : \"" + mainPic + "\",\n" +
                "  \"fileGallery\" : [ \"" + fileGallery + "\" ],\n" +
                "  \"facts\" : [ {\n" +
                "    " + facts + "\n" +
                "  } ]\n" +
                "}";
    }

    private String cleanString(String s)
    {
        return s.replaceAll("\\s", "");
    }

    @AfterMethod
    public void teardown()
    {
        userRepository.deleteAll();
        pictureRepository.deleteAll();
        productInfoRepository.deleteAll();
        vendorInfoRepository.deleteAll();
        vendorRepository.deleteAll();
    }
}
