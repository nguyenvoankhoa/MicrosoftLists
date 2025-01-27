package com.model;

import com.model.view.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Component
public class SmartList extends Template {
    private List<Form> forms;
    private List<Row> rows;
    private List<View> views;

    public SmartList(String name) {
        super(name);
        this.forms = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.views = new ArrayList<>();
    }


}
