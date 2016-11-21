package de.haw.aim.vendor;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import de.haw.aim.vendor.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    VendorInfoRepository vendorInfoRepository;

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    VendorComponent vendorComponent;

    @Autowired
    UserRepository userRepository;

    private String name;
    private String shortDescription;
    private String longDescription;

    private String username;
    private String pw;

    Picture picture;

    VendorInfo vendorInfo;

    List<ProductInfo> productInfoList;

    ProductInfo productInfoOne;
    ProductInfo productInfoTwo;

    List<String> productInfoIdsList;

    Vendor vendor;

    User user;

    @BeforeMethod
    public void setUp() throws Exception
    {
        username = "Hans";
        pw = "Safety123!";

        name = "Name String";
        shortDescription = "Short Description";
        longDescription  = "Looooooonger Description";

        picture = new Picture("dog-1742295_640.jpg");
        picture = pictureRepository.save(picture);

        user = new User(username,pw);

        user = userRepository.save(user);

        vendorInfo = new VendorInfo(
                name,
                shortDescription,
                longDescription,
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );

        vendorInfo = vendorInfoRepository.save(vendorInfo);

        productInfoList = new ArrayList<>();

        productInfoOne = new ProductInfo(
                "Name",
                "Short Description",
                "Long Description",
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );

        productInfoTwo = new ProductInfo(
                "Name",
                "Short Description",
                "Long Description",
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );

        productInfoOne = productInfoRepository.save(productInfoOne);
        productInfoTwo = productInfoRepository.save(productInfoTwo);

        productInfoIdsList = new ArrayList<>();

        productInfoIdsList.add(productInfoOne.getId());
        productInfoIdsList.add(productInfoTwo.getId());

        productInfoList.add(productInfoOne);
        productInfoList.add(productInfoTwo);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        vendor = new Vendor(
                vendorInfo,
                productInfoList,
                userList,
                new ArrayList<>()
        );

        vendor = vendorRepository.save(vendor);
    }

    @Test
    public void setVendorInfoTest()
    {
        Assert.assertEquals(vendorComponent.getVendor(vendor.getId()).getId(),vendorInfo.getId());
        VendorInfo differentVendorInfo = new VendorInfo(
                name,
                shortDescription,
                longDescription,
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );
        differentVendorInfo = vendorInfoRepository.save(differentVendorInfo);

        Assert.assertNotEquals(differentVendorInfo.getId(), vendor.getId());

        vendor.setVendorInfo(differentVendorInfo);
        vendor = vendorRepository.save(vendor);

        Assert.assertEquals(differentVendorInfo.getId(), vendor.getId());
    }

    @Test
    public void getProductInfoIdsTest()
    {
        boolean identical = vendor.getProdcutInfoIds().containsAll(productInfoIdsList) &&
                productInfoIdsList.containsAll(vendor.getProdcutInfoIds());

        Assert.assertTrue(identical);
    }

    @Test
    public void getVendorForUserTest()
    {
        Assert.assertEquals(vendor,vendorComponent.getVendor(user));
    }

    @Test
    public void putVendorTest()
    {
        VendorInfo differentVendorInfo = new VendorInfo(
                "Different Name",
                shortDescription,
                longDescription,
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );

        differentVendorInfo = vendorInfoRepository.save(differentVendorInfo);
        Assert.assertNotEquals(differentVendorInfo.getId(), vendor.getId());

        differentVendorInfo.setId(vendor.getId());
        differentVendorInfo = vendorInfoRepository.save(differentVendorInfo);
        Assert.assertEquals(differentVendorInfo.getId(),vendor.getId());

        Assert.assertNotEquals(differentVendorInfo.getName(),vendor.getVendorInfo().getName());
        vendor = vendorComponent.putVendor(differentVendorInfo);

        Assert.assertEquals(differentVendorInfo.getName(),vendor.getVendorInfo().getName());
    }

    @Test
    public void putVendorNullTest()
    {
        VendorInfo differentVendorInfo = new VendorInfo(
                "Different Name",
                shortDescription,
                longDescription,
                picture,
                new ArrayList<>(),
                new ArrayList<>()
        );

        differentVendorInfo = vendorInfoRepository.save(differentVendorInfo);
        Assert.assertNull(vendorComponent.putVendor(differentVendorInfo));
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
