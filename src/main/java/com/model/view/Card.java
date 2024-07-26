package com.model.view;

import com.model.datatype.IData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card{
    private IData data;
    private String shape;
    private String color;

    public Card(IData data) {
        this.data = data;
    }
}
