package com.js.calendar.service;

import com.js.calendar.entities.User;
import com.js.calendar.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@DataJpaTest   //use spring to get beans from context
@ExtendWith(MockitoExtension.class) // use only mockito
public class BaseServiceUserEntityTest {

    private User testUserMock;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository);

    @BeforeEach
    private void beforeEach() {
        testUserMock = new User();
        testUserMock.setId(1L);
        testUserMock.setUsername("John Snow");
        testUserMock.setPassword("super_secure_password");
        testUserMock.setEnabled(true);
        testUserMock.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        testUserMock.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @Test
    void getAllUsersTest() {
        //given
        List<User> repositoryValues = new ArrayList<>();
        repositoryValues.add(testUserMock);
        when(userRepository.findAll()).thenReturn(repositoryValues);

        //when
        Iterable<User> users = userService.getEntities();

        //then
        assertNotNull(users);
        assertEquals(repositoryValues.size(), iterableToList(users).size());
        assertEquals(repositoryValues, iterableToList(users));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserByIdWhenUserIsPresentTest() {
        //given
        Optional<User> userMock = Optional.of(this.testUserMock);
        when(userRepository.findById(anyLong())).thenReturn(userMock);

        //when
        Optional<User> testEntity = userService.getEntity(anyLong());

        //then
        assertEquals(testEntity, userMock);
        assertTrue(testEntity.isPresent());
        assertEquals(testEntity.get(), userMock.get());
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void getUserByIdWhenUserIsNotPresentTest() {
        //given
        Optional<User> resultUser = Optional.empty();
        when(userRepository.findById(anyLong())).thenReturn(resultUser);

        //when
        Optional<User> testEntity = userService.getEntity(anyLong());

        //test
        assertEquals(testEntity, resultUser);
        assertFalse(testEntity.isPresent());
    }

    @Test
    void deleteUserWhenIdPresentTest() {
        //given
        doNothing().when(userRepository).deleteById(anyLong());

        //when
        userService.deleteEntity(anyLong());
        userService.deleteEntity(anyLong());
        userService.deleteEntity(anyLong());

        //then
        verify(userRepository, times(3)).deleteById(anyLong());
    }

    @Test
    void addUserTest() {
        //given
        User addUser = new User();
        addUser.setId(2L);
        addUser.setUsername("Mila Kunis");
        addUser.setPassword("super_secure_password_For_real");
        addUser.setEnabled(false);
        addUser.setCreatedDate(Timestamp.valueOf(LocalDateTime.MIN));
        addUser.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.MIN));
        when(userRepository.save(addUser)).thenAnswer(mock -> mock.getArgument(0, User.class));

        //when
        userService.addEntity(addUser);

        //then
        assertEquals(addUser, userRepository.save(addUser));
        verify(userRepository, times(2)).save(addUser);
    }

    @Test
    void updateExistingUserTest() {
        //given
        User updateUser = new User();
        updateUser.setUsername("Mila Kunis");
        updateUser.setPassword("super_secure_password_For_real");
        updateUser.setEnabled(false);
        updateUser.setCreatedDate(Timestamp.valueOf(LocalDateTime.MAX));
        updateUser.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.MAX));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUserMock));

        //when
        userService.updateEntity(1L, updateUser);

        //then
        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(updateUser);
    }

    @Test()
    void updateNotExistingUserTest() {
        //given
        User updateUser = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        assertThrows(EmptyResultDataAccessException.class, () -> userService.updateEntity(1L, updateUser));

        //then
        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(0)).save(updateUser);
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
