package com.appcentcasestudy.dataAccess.abstracts;

import com.appcentcasestudy.entities.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListDao extends JpaRepository<ToDoList,Long> {
}
