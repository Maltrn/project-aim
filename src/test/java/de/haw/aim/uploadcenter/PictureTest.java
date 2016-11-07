package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.persistence.*;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PictureTest extends AbstractTestNGSpringContextTests {

    private File file;

    @Autowired
    protected PictureRepository repo;

    @BeforeMethod
    public void setUp() throws Exception {
        this.file = new Picture("dog-1742295_640.jpg");
        this.repo.deleteAll();
        repo.insert((Picture) this.file);
    }

    @Test
    public void testPictureConstructor() throws Exception {
        Assert.assertNotNull(this.file);
    }

    @Test
    public void testPictureNotEmpty() throws Exception {
        Assert.assertNotNull(this.file);
        Assert.assertEquals(this.file.getName(), "dog-1742295_640");
        Assert.assertEquals(this.file.getLocation(), "dog-1742295_640.jpg");
    }

    @Test
    public void testIsValid() throws Exception {
        logger.warn(this.file.toString());
        this.file.validate();
    }

    @Test(expectedExceptions = ValueDoesntValidateToConfigFileException.class)
    public void testIsNotValid() throws Exception {
        this.file = new Picture("pdf-sample.pdf");

        this.file.validate();
    }

    @Test
    public void testDBInsertion() throws Exception {
        Picture dbEntity = this.repo.findAll().get(0);
        Assert.assertEquals(dbEntity.getLocation(), "dog-1742295_640.jpg");
    }
}
