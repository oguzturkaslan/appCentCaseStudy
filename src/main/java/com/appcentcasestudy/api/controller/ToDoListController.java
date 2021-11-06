package com.appcentcasestudy.api.controller;

import com.appcentcasestudy.business.abstracts.ToDoListService;
import com.appcentcasestudy.business.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todolist")
public class ToDoListController {

    private ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }
}
