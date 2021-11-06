package com.appcentcasestudy.business.concretes;

import com.appcentcasestudy.business.abstracts.ToDoListService;
import com.appcentcasestudy.core.dtos.ToDoListUpdateDto;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.entities.ToDoList;
import org.springframework.stereotype.Service;

@Service
public class ToDoListManager implements ToDoListService {
    @Override
    public Result createTask(ToDoList toDoList) {
        return null;
    }

    @Override
    public Result updateTask(Long id, ToDoListUpdateDto toDoListUpdateDto) {
        return null;
    }

    @Override
    public Result deleteTask(Long id) {
        return null;
    }
}
