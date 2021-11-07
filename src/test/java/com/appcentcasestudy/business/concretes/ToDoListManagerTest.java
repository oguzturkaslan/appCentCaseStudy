package com.appcentcasestudy.business.concretes;

import com.appcentcasestudy.core.dtos.ToDoListUpdateDto;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.core.utilities.results.SuccessResult;
import com.appcentcasestudy.dataAccess.abstracts.ToDoListDao;
import com.appcentcasestudy.dataAccess.abstracts.UserDao;
import com.appcentcasestudy.entities.ToDoList;
import com.appcentcasestudy.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListManagerTest {

    private ToDoListManager toDoListManager;
    private ToDoListDao toDoListDao;
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        userDao = Mockito.mock(UserDao.class);
        toDoListDao = Mockito.mock(ToDoListDao.class);
        toDoListManager = new ToDoListManager(userDao, toDoListDao);
        bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
    }

    @Test
    public void whenCreateTaskCalledWithValidRequest_itShouldReturnResult() {
        User user = new User();
        user.setId(1L);
        user.setEmail("qweqweqwe@qwe.com");
        user.setPassword(bCryptPasswordEncoder.encode("123123"));

        ToDoList toDoList = new ToDoList();
        toDoList.setId(1L);
        toDoList.setDescription("deneme");
        toDoList.setDone(false);
        toDoList.setUser(user);

        ToDoList createdToDoList = new ToDoList();
        createdToDoList.setId(toDoList.getId());
        createdToDoList.setDescription(toDoList.getDescription());
        createdToDoList.setDone(toDoList.isDone());

        Result testResult = new SuccessResult(true);

        Mockito.when(userDao.existsById(toDoList.getUser().getId())).thenReturn(true);
        Mockito.when(toDoListDao.existsByDescription(toDoList.getDescription())).thenReturn(false);
        Mockito.when(toDoListDao.save(toDoList)).thenReturn(createdToDoList);

        Result result = toDoListManager.createTask(toDoList);

        Assertions.assertEquals(result.isSuccess(), testResult.isSuccess());

        Mockito.verify(userDao).existsById(toDoList.getUser().getId());
        Mockito.verify(toDoListDao).existsByDescription(toDoList.getDescription());
        Mockito.verify(toDoListDao).save(toDoList);
    }

    @Test
    public void whenUpdateTaskCalledWithValidRequest_itShouldReturnResult() {
        String newDescription = "testDenemeUpdate";
        User user = new User();
        user.setId(1L);
        user.setEmail("qweqweqwe@qwe.com");
        user.setPassword(bCryptPasswordEncoder.encode("123123"));

        ToDoList toDoList = new ToDoList();
        toDoList.setId(1L);
        toDoList.setDescription("deneme");
        toDoList.setDone(false);
        toDoList.setUser(user);

        ToDoListUpdateDto createdToDoListDto = new ToDoListUpdateDto();
        createdToDoListDto.setDescription(newDescription);
        createdToDoListDto.setDone(false);

        ToDoList createdToDoList = new ToDoList();
        createdToDoList.setId(toDoList.getId());
        createdToDoList.setDescription(createdToDoListDto.getDescription());
        createdToDoList.setDone(createdToDoListDto.isDone());

        Result testResult = new SuccessResult(true);

        Mockito.when(toDoListDao.existsById(toDoList.getId())).thenReturn(true);
        Mockito.when(toDoListDao.getById(toDoList.getId())).thenReturn(toDoList);
        Mockito.when(toDoListDao.save(toDoList)).thenReturn(createdToDoList);

        Result result = toDoListManager.updateTask(toDoList.getId(), createdToDoListDto);

        Assertions.assertEquals(result.isSuccess(), testResult.isSuccess());

        Mockito.verify(toDoListDao).existsById(toDoList.getId());
        Mockito.verify(toDoListDao).getById(toDoList.getId());
        Mockito.verify(toDoListDao).save(toDoList);


    }


    @Test
    public void whenDeleteTaskCalledWithValidRequest_itShouldReturnResult() {
        User user = new User();
        user.setId(1L);
        user.setEmail("qweqweqwe@qwe.com");
        user.setPassword(bCryptPasswordEncoder.encode("123123"));

        ToDoList toDoList = new ToDoList();
        toDoList.setId(1L);
        toDoList.setDescription("deneme");
        toDoList.setDone(false);
        toDoList.setUser(user);

        userDao.deleteById(toDoList.getId());

        Assertions.assertEquals(userDao.count(), 0);


    }

}