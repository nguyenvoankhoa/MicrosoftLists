package com;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private String username;
    private String message;

    public Comment(String username, String message) {
        this.username = username;
        this.message = message;
    }
}
