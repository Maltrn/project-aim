package de.haw.aim.rest;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import de.haw.aim.vendor.persistence.ProductInfo;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    Controller controller;

    @BeforeMethod
    public void setUp() throws Exception
    {
        Picture picture = new Picture();
        VendorInfo vendorInfo = new VendorInfo("Vendor", "short description", "long description", );
        List<User> users = new ArrayList<>();
        List<UploadedFile> uploadedFiles = new ArrayList<>();

        Vendor testVendor = new Vendor();

    }

    @Test
    public void testVendorGet() throws Exception
    {
        //get("/lotto").then().assertThat().body("lotto.lottoId", equalTo(5));
    }
}
