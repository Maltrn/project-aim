package de.haw.aim.authentication;

import de.haw.aim.authentication.persistence.User;
import de.haw.aim.authentication.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private
    AuthenticationCompoment authenticationCompoment;

    @Autowired
    private
    UserRepository userRepository;

    private User hans;
    private String username;
    private String password;

    @BeforeClass
    public void setUp()
    {
        username = "Hans";
        password = "Sicherheit123";
        hans = authenticationCompoment.create(username, password);
    }

    @Test
    public void loginTest()
    {
        Assert.assertEquals(hans,authenticationCompoment.login(username, password));
        Assert.assertNull(authenticationCompoment.login(username, "Sicherheit456"));
        Assert.assertNull(authenticationCompoment.login("Franz", password));
    }

    @Test
    public void tokenTest()
    {
        hans = authenticationCompoment.login(username, password);
        String token = hans.getCurrentToken();
        Assert.assertTrue(authenticationCompoment.verifyToken(token));
        String someRandomToken = "this is probably a non valid token, holy moly imagine if this test ever fails...";
        Assert.assertFalse(authenticationCompoment.verifyToken(someRandomToken));

        Assert.assertEquals(hans, authenticationCompoment.findByToken(token));
    }

    @AfterClass
    public void teardown()
    {
        userRepository.deleteAll();
    }

}
