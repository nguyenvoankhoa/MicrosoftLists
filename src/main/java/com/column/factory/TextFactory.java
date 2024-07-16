package com.column.factory;

import com.column.datatype.Data;
import com.column.datatype.Text;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TextFactory implements DataFactory {
    @Override
    public Data createData() {
        return new Text();
    }
}
