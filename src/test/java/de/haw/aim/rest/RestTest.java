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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;

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

    @BeforeMethod
    public void setUp() throws Exception
    {
        Picture picture = new Picture("testPath");
        List<UploadedFile> fileGallery = new ArrayList<>();
        fileGallery.add(picture);

        Fact fact = new Fact("key", "value");
        List<Fact> facts = new ArrayList<>();
        facts.add(fact);

        VendorInfo vendorInfo = new VendorInfo("Vendor", "short description", "long description", picture, fileGallery, facts);

        ProductInfo productInfo = new ProductInfo("productName", "sort Description", "long Description", picture, fileGallery, facts);
        List<ProductInfo> productInfos = new ArrayList<>();
        productInfos.add(productInfo);

        User user = new User("userName", "password");
        List<User> users = new ArrayList<>();
        users.add(user);

        Vendor testVendor = new Vendor(vendorInfo, productInfos, users, fileGallery);

        pictureRepository.save(picture);
        vendorInfoRepository.save(vendorInfo);
        productInfoRepository.save(productInfo);
        userRepository.save(user);
        vendorRepository.save(testVendor);

        RestAssured.basePath = "/api";
    }

    @Test
    public void testVendorGet() throws Exception
    {
        when()
                .get("/vendor")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", hasItems("58299303ba136171d331c75a"));
    }
}
