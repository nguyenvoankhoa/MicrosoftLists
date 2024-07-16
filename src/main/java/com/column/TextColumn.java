package com.column;

import com.column.factory.DataFactory;
import com.column.factory.TextFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextColumn implements IColumn<String> {

    public static final ColumnType type = ColumnType.TEXT;

    private String name;
    private String text;

    @Override
    public String getData() {
        return this.text;
    }

    @Override
    public void setData(String data) {
        this.text = data;
    }

    @Override
    public DataFactory<?> getDataFactory() {
        return new TextFactory();
    }
}
