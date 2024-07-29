package com.view;

import com.model.Row;
import com.view.mapper.DataToDTOMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RowDTO {
    private List<Object> iDataList;

    public RowDTO(Row row) {
        this.iDataList = row.getIDataList().stream()
                .map(DataToDTOMapper::map)
                .toList();
    }
}

