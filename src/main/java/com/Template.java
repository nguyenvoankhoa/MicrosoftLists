package com;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Template {
    private List<Column> columns;
    private ListView listView;
}
