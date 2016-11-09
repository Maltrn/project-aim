package de.haw.aim.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest extends AbstractTestNGSpringContextTests {
    @Autowired
    Controller controller;

    @Test
    public void testRest()
    {

    }
}
