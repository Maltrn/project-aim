package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.facade.IUploadCenter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.testng.Assert.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadCenterTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private UploadCenter uploadCenter;

    @BeforeMethod
    public void setUp() throws Exception
    {
        FileUtils.cleanDirectory(Paths.get("./src/test/resources/testFiles").toFile());
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