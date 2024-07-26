package com.model;

import com.model.datatype.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private Person person;
    private String message;

}
