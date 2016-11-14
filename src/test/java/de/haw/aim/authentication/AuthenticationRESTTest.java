package de.haw.aim.authentication;

import de.haw.aim.AIMServer;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = AIMServer.class)
public class AuthenticationRESTTest extends AbstractTestNGSpringContextTests {

    @Autowired
    AuthenticationInterface authenticationInterface;

    @BeforeClass
    public void setup() {
        authenticationInterface.create("wilhelm", "apfelstrudel");
        RestAssured.basePath = "/api";
    }

    @Test
    public void loginSucessfulTest() {
        given().
                header("Content-Type", "application/json").
                body("{ \"username\": \"wilhelm\", \"password\": \"apfelstrudel\" }").
        when().
                post("/login").
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void tokenSecuredEndpointSucessfulTest() {
        given().
                header("Authorization", "TOKEN handsomeTOKEN").
        when().
                put("/vendor").
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void tokenSecuredEndpointFailedNoTokenGivenTest() {
        when().
                put("/vendor").
                then().
                statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void tokenSecuredEndpointFailedInvalidTokenTest() {
        given().
                header("Authorization", "TOKEN invalidToken").
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

}
