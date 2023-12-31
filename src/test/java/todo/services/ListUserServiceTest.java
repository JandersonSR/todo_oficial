package todo.services;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import todo.domains.entities.UserEntity;
import todo.domains.repository.UserRepository;

import todo.services.imp.UserServiceImp;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListUserServiceTest {

    @InjectMocks
    UserServiceImp userServiceImp;

    @Mock
    UserRepository userRepository;

    UserEntity userEntity;

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
        this.userEntity = new UserEntity(
                "Joaquim murtinho",
                "31432196880",
                "teste@mail.com",
                "123",
                "USER",
                LocalDate.now()
        );
    }
    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));
        UserEntity filter = new UserEntity();

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        List<UserEntity> userList = userServiceImp.list(example);
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {

//        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abcZ".contains("Z"));
//        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }
}
