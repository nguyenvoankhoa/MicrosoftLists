package com;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Item {
    private Object data;
    private List<Comment> comments;
}
