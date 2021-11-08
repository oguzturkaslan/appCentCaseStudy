package com.appcentcasestudy.business.concretes;

import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.core.utilities.results.SuccessResult;
import com.appcentcasestudy.dataAccess.abstracts.UserDao;
import com.appcentcasestudy.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

class UserManagerTest {


    private UserManager userManager;
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        userDao = Mockito.mock(UserDao.class);
        bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        userManager = new UserManager(userDao, bCryptPasswordEncoder);
    }

    @Test
    public void whenRegisterAccountCalledWithValidRequest_itShouldReturnResult() {
        User user = new User();
        user.setId(1L);
        user.setEmail("qweqweqwe@qwe.com");
        user.setPassword(bCryptPasswordEncoder.encode("123123"));

        User createdUser = new User();
        createdUser.setId(user.getId());
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(user.getPassword());
        MockedStatic<UserManager> utilities = Mockito.mockStatic(UserManager.class);

        Result testResult = new SuccessResult(true);

        Mockito.when(userManager.checkIfUserExist("qweqweqwe@qwe.com")).thenReturn(false);
        utilities.when(() -> UserManager.validate("qweqweqwe@qwe.com")).thenReturn(true);
        Mockito.when(userDao.save(user)).thenReturn(createdUser);

        Result result = userManager.register(user);

        Assertions.assertEquals(result.isSuccess(), testResult.isSuccess());

        Mockito.verify(userDao).existsByEmail("qweqweqwe@qwe.com");
        Mockito.verify(userDao).save(user);
    }

    @Test
    public void whenLoginAccountCalledWithValidRequest_itShouldReturnResult() {
      /*  List<User> users = new ArrayList<>();

        User user = new User();
        user.setId(1L);
        user.setEmail("qweqweqwe@qwe.com");
        user.setPassword(bCryptPasswordEncoder.encode("123123"));
        users.add(user);

        Result testResult = new SuccessResult(true);

        Mockito.when(userDao.findAll()).thenReturn(users);

        Result result = userManager.login(user);

        Assertions.assertEquals(result.isSuccess(), testResult.isSuccess());

        Mockito.verify(userDao).findAll();*/


    }


}