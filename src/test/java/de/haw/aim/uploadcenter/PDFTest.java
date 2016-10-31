package de.haw.aim.uploadcenter;

import de.haw.aim.uploadcenter.persistence.File;
import de.haw.aim.uploadcenter.persistence.PDF;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PDFTest {

    private File file;

    @BeforeMethod
    public void setUp() throws Exception {
        this.file = new PDF();
    }

    @Test
    public void testPDFConstructor() throws Exception {
        Assert.assertNotNull(this.file);
    }

    @Test
    public void testPDFNotEmpty() throws Exception {
        this.file = new PDF("pdf-sample.pdf", "test pdf");
        Assert.assertNotNull(this.file);
        Assert.assertEquals(this.file.getName(), "test pdf");
        Assert.assertEquals(this.file.getFile().getPath(), "pdf-sample.pdf");
    }
}
