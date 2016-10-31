package de.haw.aim.uploadcenter;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DocumentTest {

    private Document documentFile;

    @BeforeMethod
    public void setUp() throws Exception {
        this.documentFile = new Document();
    }

    @Test
    public void testFileType() throws Exception {
        Assert.assertNotNull(this.documentFile);
        Assert.assertEquals(this.documentFile.getType, "Document");
    }
}
