package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.persistence.PDF;
import de.haw.aim.uploadcenter.persistence.PDFRepository;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PDFTest extends AbstractTestNGSpringContextTests {

    private UploadedFile file;

    @Autowired
    private PDFRepository repo;

    @BeforeMethod
    public void setUp() throws Exception {
        this.file = new PDF("pdf-sample.pdf");
        this.repo.deleteAll();
        repo.save((PDF) this.file);
    }

    @Test
    public void testPDFConstructor() throws Exception {
        Assert.assertNotNull(this.file);
    }

    @Test
    public void testPDFNotEmpty() throws Exception {
        Assert.assertNotNull(this.file);
        Assert.assertEquals(this.file.getName(), "pdf-sample");
        Assert.assertEquals(this.file.getLocation(), "pdf-sample.pdf");
    }

    @Test
    public void testIsValid() throws Exception {
        this.file.validate();
    }

    @Test(expectedExceptions = ValueDoesntValidateToConfigFileException.class)
    public void testIsNotValid() throws Exception {
        this.file = new PDF("dog-1742295_640.jpg");
        this.file.validate();
    }

    @Test
    public void testDBInsertion() throws Exception {
        PDF dbEntity = this.repo.findAll().get(0);
        Assert.assertEquals(dbEntity.getName(), "pdf-sample");
    }
}
