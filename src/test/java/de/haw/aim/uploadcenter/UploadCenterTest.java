package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.persistence.PDFRepository;
import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.PictureRepository;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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

    @Value("${uploadcenter.fileslocation}")
    private String uploadedFileLocation;

    @BeforeMethod
    public void setUp() throws Exception
    {
        FileUtils.forceMkdir(Paths.get(uploadedFileLocation).toFile());
        FileUtils.cleanDirectory(Paths.get(uploadedFileLocation).toFile());
        pictureRepository.deleteAll();
        pdfRepository.deleteAll();
    }

    @Test
    public void testUploadFile() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        UploadedFile persistedFile = this.uploadCenter.uploadFile(file);
        File newFile = new File(uploadedFileLocation + "/b.png");
        Assert.assertTrue(newFile.exists());
        Assert.assertTrue(pictureRepository.exists(persistedFile.getId()));
    }

    @Test(expectedExceptions = StorageException.class)
    public void testUploadIllegalFileType() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("foobar", "foobar.svg", "image/svg", "nonsensecontent".getBytes());
        this.uploadCenter.uploadFile(file);
    }

    @Test(expectedExceptions = StorageException.class)
    public void testUploadEmptyFile() throws Exception
    {
        MockMultipartFile emptyFile = new MockMultipartFile(" ", new byte[0]);
        this.uploadCenter.uploadFile(emptyFile);
    }

    @Test(expectedExceptions = StorageException.class)
    public void testUploadNull() throws Exception
    {
        this.uploadCenter.uploadFile(null);
    }

    @Test
    public void testReplaceFile() throws Exception
    {
        MockMultipartFile file1 = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("a", "a.png", "image/png", "asdasdasd".getBytes());
        UploadedFile uploadedFile = this.uploadCenter.uploadFile(file1);
        this.uploadCenter.replaceFile(uploadedFile.getId(), file2);
        File replacedFile = new File(uploadedFileLocation + "/a.png");
        File oldFile = new File(uploadedFileLocation + "/b.png");
        Assert.assertTrue(replacedFile.exists());
        Assert.assertTrue(!oldFile.exists());
        UploadedFile dbResult = pictureRepository.findOne(uploadedFile.getId());
        Assert.assertEquals(dbResult.getLocation(), "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testFiles" + File.separator + "a.png");
        Assert.assertEquals(pictureRepository.findAll().size(), 1);
    }

    @Test(expectedExceptions = StorageException.class)
    public void testReplaceNotExistentFile() throws Exception
    {
        this.uploadCenter.replaceFile("foobar", null);
    }

    @Test(expectedExceptions = StorageException.class)
    public void testReplaceEmptyFile() throws Exception
    {
        MockMultipartFile emptyFile = new MockMultipartFile(" ", new byte[0]);
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        UploadedFile persistedFile = this.uploadCenter.uploadFile(file);
        Assert.assertEquals(this.pictureRepository.findAll().size(), 1);
        this.uploadCenter.replaceFile(persistedFile.getId(), emptyFile);
    }

    @Test
    public void testDeleteFile() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        UploadedFile deleteThis = this.uploadCenter.uploadFile(file);
        this.uploadCenter.deleteFile(deleteThis.getId());
        File newFile = new File(uploadedFileLocation + "/b.png");
        Assert.assertTrue(!newFile.exists());
        Assert.assertTrue(!pictureRepository.exists(deleteThis.getId()));
    }

    @Test
    public void testCheckForExistence() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        UploadedFile uploadedFile = this.uploadCenter.uploadFile(file);
        Assert.assertTrue(this.uploadCenter.checkForExistence(uploadedFile.getId()));
    }

    @Test
    public void testCheckForExistenceFail() throws Exception
    {
        Assert.assertTrue(!this.uploadCenter.checkForExistence("foobar"));
    }

    @Test
    public void testFindById() throws Exception
    {
        MockMultipartFile file = new MockMultipartFile("b", "b.png", "image/png", "nonsensecontent".getBytes());
        UploadedFile uploadedFile = this.uploadCenter.uploadFile(file);
        UploadedFile result = this.uploadCenter.findById(uploadedFile.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getClass(), Picture.class);
    }

    @Test
    public void testFindByIdNull() throws Exception
    {
        UploadedFile result = this.uploadCenter.findById("foobar");
        Assert.assertNull(result);
    }
}