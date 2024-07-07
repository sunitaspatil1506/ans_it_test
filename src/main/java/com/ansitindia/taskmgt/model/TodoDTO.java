package com.ansitindia.taskmgt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO {
//    @Id
//    private String id;

    @Id
    private Long id;  // Using Long for auto-incrementing ID
    @NotNull(message = "Title con not be null")
    private String todo;
    @NotNull(message = "Description con not be null")
    private String description;
    @NotNull(message = "Completed con not be null")
    private Boolean completed;
    private Date createdAt;
    private Date updateAt;
}
