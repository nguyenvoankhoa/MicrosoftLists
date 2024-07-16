package com;

import com.column.IColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Template {
    private String title;
    private List<IColumn> columns;
    private ListView listView;

    public Template(String title) {
        this.title = title;
        this.columns = new ArrayList<>();
    }
}
