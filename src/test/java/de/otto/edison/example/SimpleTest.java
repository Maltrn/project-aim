package de.otto.edison.example;

import org.testng.Assert;
import org.testng.annotations.*;

public class SimpleTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test()
    public void aSlowTest() {
        Assert.assertNotNull(null);
    }
}
