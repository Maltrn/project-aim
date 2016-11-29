package de.haw.aim.rest;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import de.haw.aim.vendor.persistence.*;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileTest extends AbstractTestNGSpringContextTests
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
    private File file;
    private File fileToDelete;

    private String productName = "productName";
    private String shortDescription = "short Description";
    private String longDescription = "long description";
    @Value("${local.server.port}")
    private int port;

    String path = "C:\\Users\\Rene\\Documents\\Workspaces\\IntelliJ\\projecttwomicroservice\\src\\test\\resources\\dog-1742295_640.jpg";

    @BeforeMethod
    public void setUp() throws IOException
    {
        fileToDelete = new File("src\\test\\resources\\testFiles\\dog-1742295_640.jpg");
        fileToDelete.delete();
        file = new File(path);
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

    @AfterMethod
    public void teardown()
    {
        userRepository.deleteAll();
        pictureRepository.deleteAll();
        productInfoRepository.deleteAll();
        vendorInfoRepository.deleteAll();
        vendorRepository.deleteAll();
    }

    @Test
    public void uploadFileTest()
    {
        String pictureID =
                given()
                .contentType("multipart/form-data")
                .header("Authorization", "TOKEN handsomeTOKEN")
                .multiPart(file)
                .when()
                .put("/file")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

        assertNotNull(pictureRepository.findOne(pictureID));
    }
}