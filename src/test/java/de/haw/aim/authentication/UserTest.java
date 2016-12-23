package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private
    UserRepository userRepository;

    @Test
    public void saveUserTest()
    {
        User hans = new User("Hans", "Sicherheit123");
        Assert.assertTrue(hans.equals(userRepository.save( hans )));
    }

    @AfterMethod
    public void teardown()
    {
        userRepository.deleteAll();
    }

}
