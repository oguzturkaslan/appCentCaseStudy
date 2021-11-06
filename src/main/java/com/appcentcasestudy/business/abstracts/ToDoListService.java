package com.appcentcasestudy.business.abstracts;

import com.appcentcasestudy.core.dtos.ToDoListUpdateDto;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.entities.ToDoList;

public interface ToDoListService {

    Result createTask(ToDoList toDoList);

    Result updateTask(Long id, ToDoListUpdateDto toDoListUpdateDto);

    Result deleteTask(Long id);
}
