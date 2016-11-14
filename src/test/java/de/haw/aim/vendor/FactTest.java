package de.haw.aim.vendor;

import de.haw.aim.vendor.persistence.Fact;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FactTest extends AbstractTestNGSpringContextTests {
    private String key;
    private String value;
    private Fact factOne;
    private Fact factTwo;

    @BeforeMethod
    public void setUp()
    {
        key   = "This is in fact a key";
        value = "This is in fact a value";
        factOne = new Fact(key,value);
        factTwo = new Fact(key,value);
    }

    @Test
    public void testFact()
    {
        Assert.assertEquals(factOne,factTwo);
        Assert.assertEquals(false, factOne == factTwo);
        Assert.assertEquals(key, factOne.getKey());
        Assert.assertEquals(value, factOne.getValue());
    }
}
