package com.example.demo;


import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // given
        UserEntity alex = new UserEntity();
        alex.setUsername("alex");
        alex.setPassword("123");
        alex.setVerified(false);
        alex.setEmail("alex@mail.com");
        alex.setRole(Role.STUDENT);

        entityManager.persist(alex);
        entityManager.flush();

        // when
        UserEntity found = userRepository.findByUsername(alex.getUsername());

        // then
        assertThat(found.getUsername())
                .isEqualTo(alex.getUsername());
        assertThat(found.isVerified())
                .isEqualTo(alex.isVerified());
        assertThat(found.getEmail())
                .isEqualTo(alex.getEmail());
        assertThat(found.getRole())
                .isEqualTo(alex.getRole());

    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        // given
        UserEntity alex = new UserEntity();
        alex.setUsername("alex");
        alex.setPassword("123");
        alex.setVerified(false);
        alex.setEmail("alex@mail.com");
        alex.setRole(Role.STUDENT);

        entityManager.persist(alex);
        entityManager.flush();

        // when
        UserEntity found = userRepository.findByEmail(alex.getEmail());

        // then
        assertThat(found.getUsername())
                .isEqualTo(alex.getUsername());
        assertThat(found.isVerified())
                .isEqualTo(alex.isVerified());
        assertThat(found.getEmail())
                .isEqualTo(alex.getEmail());
        assertThat(found.getRole())
                .isEqualTo(alex.getRole());

    }

    @Test
    public void notExists_returnNull() {
        // when
        UserEntity found = userRepository.findByEmail("alaki@gmail.com");


        // then
        assertThat(found)
                .isNull();

    }

}