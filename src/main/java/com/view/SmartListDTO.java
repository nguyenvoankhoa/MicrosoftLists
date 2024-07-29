package com.view;

import com.model.SmartList;
import com.view.mapper.ColumnToDTOMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmartListDTO {
    private String title;
    private List<RowDTO> rows;
    private List<Object> columns;

    public SmartListDTO(SmartList smartList) {
        this.title = smartList.getName();
        this.rows = smartList.getRows().stream()
                .map(RowDTO::new)
                .toList();
        this.columns = smartList.getColumns().stream()
                .map(ColumnToDTOMapper::map)
                .toList();
    }
}
