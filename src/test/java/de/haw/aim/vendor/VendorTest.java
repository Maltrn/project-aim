package de.haw.aim.vendor;

import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import de.haw.aim.vendor.persistence.VendorInfoRepository;
import de.haw.aim.vendor.persistence.Vendor;
import de.haw.aim.vendor.persistence.VendorInfo;
import de.haw.aim.vendor.persistence.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    VendorInfoRepository vendorInfoRepository;

    @Autowired
    VendorComponent vendorComponent;

    @BeforeMethod
    public void setUp() throws Exception
    {

    }

    @Test
    public void vendorComponentTest()
    {
        String name = "Anbieter";
        String shortDescription = "Kurzbeshreibung";
        String longDescription = "Langbeschreibung";

        Picture picture = new Picture("dog-1742295_640.jpg");
        pictureRepository.save(picture);

        VendorInfo vendorInfo = new VendorInfo(
                name,
                shortDescription,
                longDescription,
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );

        vendorInfoRepository.save(vendorInfo);

        Vendor vendor = new Vendor(
                vendorInfo,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        vendor = vendorRepository.save(vendor);

        Assert.assertEquals(vendorComponent.getVendor(vendor.getId()).getId(),vendorInfo.getId());
    }
}
