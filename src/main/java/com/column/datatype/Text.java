package com.column.datatype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Text implements Data<String> {
    private String text;

    @Override
    public String getData() {
        return text;
    }

    @Override
    public void setData(String data){

    }


}
