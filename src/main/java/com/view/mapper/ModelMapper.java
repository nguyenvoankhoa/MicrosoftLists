package com.view.mapper;

import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.view.MicrosoftListDTO;
import com.view.RowDTO;
import com.view.SmartListDTO;

public class ModelMapper {

    public SmartListDTO mapSmartList(SmartList sl) {
        SmartListDTO dto = new SmartListDTO();
        dto.setName(sl.getName());
        dto.setRows(sl.getRows().stream()
                .map(this::mapRow)
                .toList());
        dto.setColumns(sl.getColumns().stream()
                .map(ColumnToDTOMapper::map)
                .toList());
        return dto;
    }


    public MicrosoftListDTO mapMicrosoftList(MicrosoftList microsoftList) {
        MicrosoftListDTO dto = new MicrosoftListDTO();
        dto.setTemplates(microsoftList.getTemplates());
        dto.setListCollection(microsoftList.getListCollection().stream()
                .map(this::mapSmartList)
                .toList());
        dto.setFavouriteCollection(microsoftList.getFavouriteCollection().stream()
                .map(this::mapSmartList)
                .toList());
        return dto;
    }

    public RowDTO mapRow(Row row) {
        RowDTO dto = new RowDTO();
        dto.setIDataList(row.getIDataList().stream()
                .map(DataToDTOMapper::map)
                .toList());
        return dto;
    }
}
