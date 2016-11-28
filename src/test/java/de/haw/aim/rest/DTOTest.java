package de.haw.aim.rest;

import de.haw.aim.rest.dto.InfoDTO;
import de.haw.aim.vendor.persistence.ProductInfo;
import de.haw.aim.vendor.persistence.VendorInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DTOTest extends AbstractTestNGSpringContextTests
{

    private InfoDTO infoDTO;
    private String id = "This is a wonderful ID";
    private String name = "This is a wonderful name";
    private String shortDescription = "This is a wonderful sort description";
    private String longDescription = "This is a wonderful loooooooooooooong description";

    private VendorInfo vendorInfo;
    private ProductInfo productInfo;


    @BeforeMethod
    public void setUp()
    {
        infoDTO = new InfoDTO();
        infoDTO.setId(id);
        infoDTO.setName(name);
        infoDTO.setShortDescription(shortDescription);
        infoDTO.setLongDescription(longDescription);

        vendorInfo = infoDTO.convertToVendorInfo();
        productInfo = infoDTO.convertToProductInfo();
    }

    @AfterMethod
    public void teardown()
    {

    }

    @Test
    public void conversionTest()
    {
        assertEquals(vendorInfo.getId(),id);
        assertEquals(productInfo.getId(),id);
        assertEquals(vendorInfo.getName(),name);
        assertEquals(productInfo.getName(),name);
        assertEquals(vendorInfo.getShortDescription(),shortDescription);
        assertEquals(productInfo.getShortDescription(),shortDescription);
        assertEquals(vendorInfo.getLongDescription(),longDescription);
        assertEquals(productInfo.getLongDescription(),longDescription);
    }
}
