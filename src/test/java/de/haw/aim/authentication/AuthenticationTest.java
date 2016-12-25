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
    AuthenticationComponent authenticationComponent;

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
        hans = authenticationComponent.create(username, password);
    }

    @Test
    public void loginTest()
    {
        Assert.assertEquals(hans, authenticationComponent.login(username, password));
        Assert.assertNull(authenticationComponent.login(username, "Sicherheit456"));
        Assert.assertNull(authenticationComponent.login("Franz", password));
    }

    @Test
    public void tokenTest()
    {
        hans = authenticationComponent.login(username, password);
        String token = hans.getCurrentToken();
        Assert.assertTrue(authenticationComponent.verifyToken(token));
        String someRandomToken = "this is probably a non valid token, holy moly imagine if this test ever fails...";
        Assert.assertFalse(authenticationComponent.verifyToken(someRandomToken));

        Assert.assertEquals(hans, authenticationComponent.findByToken(token));
    }

    @AfterClass
    public void teardown()
    {
        userRepository.deleteAll();
    }

}
