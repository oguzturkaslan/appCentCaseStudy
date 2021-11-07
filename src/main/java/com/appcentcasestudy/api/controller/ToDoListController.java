package com.appcentcasestudy.api.controller;

import com.appcentcasestudy.business.abstracts.ToDoListService;
import com.appcentcasestudy.core.dtos.ToDoListUpdateDto;
import com.appcentcasestudy.core.utilities.results.Result;
import com.appcentcasestudy.entities.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/todolist")
public class ToDoListController {

    private ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@Valid @RequestBody ToDoList toDoList){
        final Result result = toDoListService.createTask(toDoList);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@Valid @PathVariable("id") Long id, @RequestBody ToDoListUpdateDto toDoListUpdateDto) {
        final Result result = toDoListService.updateTask(id, toDoListUpdateDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@Valid @PathVariable("id") Long id){
        final Result result = toDoListService.deleteTask(id);
        return ResponseEntity.ok(result);
    }
}
