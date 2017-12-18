package manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import manager.model.User;
import manager.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTAuthorizationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Before
    public void saveUser() {
        final User user = new User();
        user.setEmail("user@user.com");
        user.setPassword(passwordEncoder.encode("pass"));
        userRepository.save(user);
    }

    @Test
    public void authorizationUserSuccessfulFindUsersTest() throws Exception {
        final String token = getToken(login("user@user.com", "pass").andReturn());
        mockMvc.perform(get("/users").header("Authorization", token)).andExpect(status().isOk());
    }

    private ResultActions login(String email, String password) throws Exception {
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return mockMvc.perform(
                post("/login").content(json(user)).contentType(MediaType.APPLICATION_JSON)
        );
    }

    private String json(Object o) throws IOException {
        return new ObjectMapper().writeValueAsString(o);
    }

    private String getToken(MvcResult result) {
        return result.getResponse().getHeader("Authorization");
    }

}