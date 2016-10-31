package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.persistence.File;
import de.haw.aim.uploadcenter.persistence.PDF;
import de.haw.aim.uploadcenter.persistence.PDFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PDFTest extends AbstractTestNGSpringContextTests {

    private File file;

    @Autowired
    protected PDFRepository repo;

    @BeforeMethod
    public void setUp() throws Exception {
        this.file = new PDF("pdf-sample.pdf");
        this.repo.deleteAll();
        repo.insert((PDF) this.file);
    }

    @Test
    public void testPDFConstructor() throws Exception {
        Assert.assertNotNull(this.file);
    }

    @Test
    public void testPDFNotEmpty() throws Exception {
        Assert.assertNotNull(this.file);
        Assert.assertEquals(this.file.getName(), "pdf-sample");
        Assert.assertEquals(this.file.getFile().getPath(), "pdf-sample.pdf");
    }

    @Test
    public void testIsValid() throws Exception {
        Assert.assertTrue(this.file.isValid());
        this.file = new PDF("dog-1742295_640.jpg");
        Assert.assertFalse(this.file.isValid());
    }

    @Test
    public void testDBInsertion() throws Exception {
        PDF dbEntity = this.repo.findAll().get(0);
        Assert.assertEquals(dbEntity.getName(), "pdf-sample");
    }
}
