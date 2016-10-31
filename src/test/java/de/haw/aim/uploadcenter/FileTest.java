package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.persistence.File;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileTest {

    private File file;

    @BeforeMethod
    public void setUp() throws Exception {
        this.file = new File();
    }

    @Test
    public void testFileConstructor() throws Exception {
        Assert.assertNotNull(this.file);
    }
}
