package com.appcentcasestudy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "todolists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "is_done",columnDefinition = "boolean default false")
    private boolean isDone;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;




}
