package com.appcentcasestudy.core.dtos;

import lombok.Data;

@Data
public class ToDoListUpdateDto {

    private static final long serialVersionUID = 1L;
    private String description;
    private boolean isDone;
}
