package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.persistence.PDFRepository;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadCenterTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private UploadCenter uploadCenter;

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @BeforeMethod
    public void setUp() throws Exception
    {
        FileUtils.cleanDirectory(Paths.get("./src/test/resources/testFiles").toFile());
        pictureRepository.deleteAll();
        pdfRepository.deleteAll();
    }

    @Test
    public void testUploadFile() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        de.haw.aim.uploadcenter.persistence.File persistedFile = this.uploadCenter.uploadFile(file);
        File newFile = new File("./src/test/resources/testFiles/b.png");
        Assert.isTrue(newFile.exists());
        Assert.isTrue(pictureRepository.exists(persistedFile.getId()));
    }

    @Test
    public void testReplaceFile() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("a", "a.png", "image/png", "nonsensecontent".getBytes());
    }

    @Test
    public void testDeleteFile() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        de.haw.aim.uploadcenter.persistence.File deleteThis = this.uploadCenter.uploadFile(file);
        this.uploadCenter.deleteFile(deleteThis.getId());
        File newFile = new File("./src/test/resources/testFiles/b.png");
        Assert.isTrue(!newFile.exists());
        Assert.isTrue(!pictureRepository.exists(deleteThis.getId()));
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