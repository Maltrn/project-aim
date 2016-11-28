package de.haw.aim.authentication;

import de.haw.aim.AIMServer;
import de.haw.aim.AppConfig;
import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import de.haw.aim.vendor.persistence.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

//@EnableConfigurationProperties
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = AIMServer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes={AIMServer.class,AppConfig.class})
public class AuthenticationRESTTest extends AbstractTestNGSpringContextTests {

    @Autowired
    AuthenticationInterface authenticationInterface;
    @Autowired
    VendorInfoRepository vendorInfoRepository;
    @Autowired
    VendorRepository vendorRepository;
    @Autowired
    PictureRepository pictureRepository;
    @Autowired
    UserRepository userRepository;

    User user;
    String currentToken;

    @BeforeMethod
    public void setup() {
        userRepository.deleteAll();
        user = authenticationInterface.create("wilhelm", "apfelstrudel");
        user = authenticationInterface.login("wilhelm","apfelstrudel");
        currentToken = user.getCurrentToken();
        RestAssured.basePath = "/api";

        String id = "this is an id";
        String name = "Name String";
        String shortDescription = "Short Description";
        String longDescription  = "Looooooonger Description";

        Picture picture = new Picture("dog-1742295_640.jpg");
        picture = pictureRepository.save(picture);

        VendorInfo vendorInfo = new VendorInfo(
                id,
                name,
                shortDescription,
                longDescription,
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );

        vendorInfo = vendorInfoRepository.save(vendorInfo);

        List<ProductInfo> productInfoList = new ArrayList<>();


        List<User> userList = new ArrayList<>();
        userList.add(user);

        Vendor vendor = new Vendor(
                vendorInfo,
                productInfoList,
                userList,
                new ArrayList<>()
        );

        vendor = vendorRepository.save(vendor);
    }

    @Test
    public void loginSucessfulTest() {
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body("{ \"username\": \"wilhelm\", \"password\": \"apfelstrudel\" }").
        when().
                post("/login").
        then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void tokenSecuredEndpointSucessfulTest() {
        given().
                header("Authorization", "TOKEN " + currentToken).
                contentType(ContentType.JSON).
                body("{ }").
        when().
                put("/vendor").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void tokenSecuredEndpointFailedNoTokenGivenTest() {
        given().
                contentType(ContentType.JSON).
        when().
                put("/vendor").
                then().
                statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void tokenSecuredEndpointFailedInvalidTokenTest() {
        given().
                header("Authorization", "TOKEN invalidToken").
                contentType(ContentType.JSON).
        when().
                put("/vendor").
                then().
                statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void tokenSecuredEndpointFailedInvalidAuthorizationHeaderTest() {
        given().
                header("Authorization", "handsomeTOKEN").
        when().
                put("/vendor").
                then().
                statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @AfterMethod
    public void teardown()
    {
        pictureRepository.deleteAll();
        vendorInfoRepository.deleteAll();
        vendorRepository.deleteAll();
        userRepository.deleteAll();
    }

}
