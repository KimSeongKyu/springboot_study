package com.study.springboot.domain.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup(){
        userRepository.deleteAll();
    }
    @Before
    public void setup(){
        userRepository.save(User.builder()
                .name("test name")
                .email("test email")
                .picture("test picture")
                .role(Role.GUEST)
                .build());
    }

    @Test
    public void loadUser() {
        //given
        String name = "test name";
        String email = "test email";
        String picture = "test picture";
        Role role = Role.GUEST;

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPicture()).isEqualTo(picture);
        assertThat(user.getRole()).isEqualTo(role);
    }

    @Test
    public void updateUser(){
        //given
        String updateName = "new name";
        String updatePicture = "new picture";

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        user.update(updateName, updatePicture);

        assertThat(user.getName()).isEqualTo(updateName);
        assertThat(user.getPicture()).isEqualTo(updatePicture);
    }

    @Test
    public void getRoleKey(){
        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        assertThat(user.getRoleKey()).isEqualTo("ROLE_GUEST");
    }
}
