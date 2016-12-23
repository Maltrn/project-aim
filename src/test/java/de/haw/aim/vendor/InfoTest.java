package de.haw.aim.vendor;

import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import de.haw.aim.vendor.persistence.Fact;
import de.haw.aim.vendor.persistence.ProductInfo;
import de.haw.aim.vendor.persistence.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private
    ProductInfoRepository productInfoRepository;

    @Autowired
    private
    PictureRepository pictureRepository;

    @BeforeMethod
    public void setUp() throws Exception
    {

    }

    @Test
    public void tryToSaveInfos()
    {
        Picture picture = new Picture("dog-1742295_640.jpg");
        pictureRepository.save(picture);

        List<UploadedFile> fileGallery = new ArrayList<>();
        fileGallery.add(picture);

        List<Fact> facts = new ArrayList<>();
        facts.add(new Fact("Beschreibung", "Beste Beschreibung"));

        String productName      = "Produkt";
        String shortDescription = "Kurzbeschreibung";
        String longDescription  = "Langbeschreibung";

        ProductInfo productInfo = new ProductInfo(productName,
                                                  shortDescription,
                                                  longDescription,
                                                  picture,
                                                  fileGallery,
                                                  facts
                                                  );

        Assert.assertEquals(productInfo, productInfoRepository.save(productInfo));
    }
}
