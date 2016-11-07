package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadCenterTest
{
    private IUploadCenter uploadCenter;

    @BeforeMethod
    public void setUp() throws Exception
    {
        this.uploadCenter = new UploadCenter("./src/test/resources/testFiles");
    }

    @Test
    public void testUploadFile() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        this.uploadCenter.uploadFile(file);
        File newFile = new File("./src/test/resources/testFiles/b.png");
        Assert.isTrue(newFile.exists());
    }

    @Test
    public void testReplaceFile() throws Exception
    {

    }

    @Test
    public void testDeleteFile() throws Exception
    {

    }

    @Test
    public void testCheckForExistence() throws Exception
    {

    }

    @Test
    public void testFindById() throws Exception
    {

    }

}