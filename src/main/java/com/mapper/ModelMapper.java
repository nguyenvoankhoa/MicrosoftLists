package com.mapper;

import com.dto.TemplateDTO;
import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.dto.MicrosoftListDTO;
import com.dto.RowDTO;
import com.dto.SmartListDTO;
import com.model.Template;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelMapper {

    private final ColumnToDTOMapper columnToDTOMapper;
    private final DataToDTOMapper dataToDTOMapper;

    public ModelMapper() {
        this.columnToDTOMapper = new ColumnToDTOMapper();
        this.dataToDTOMapper = new DataToDTOMapper();
    }

    public TemplateDTO mapTemplate(Template template) {
        TemplateDTO dto = new TemplateDTO();
        dto.setColumns(template.getColumns().stream()
                .map(columnToDTOMapper::map)
                .toList());
        dto.setName(template.getName());
        return dto;
    }

    public SmartListDTO mapSmartList(SmartList sl) {
        SmartListDTO dto = new SmartListDTO();
        dto.setName(sl.getName());
        dto.setRows(sl.getRows().stream()
                .map(this::mapRow)
                .toList());
        dto.setColumns(sl.getColumns().stream()
                .map(columnToDTOMapper::map)
                .toList());
        return dto;
    }

    public MicrosoftListDTO mapMicrosoftList(MicrosoftList microsoftList) {
        MicrosoftListDTO dto = new MicrosoftListDTO();
        dto.setTemplates(microsoftList.getTemplates().stream()
                .map(this::mapTemplate).toList());
        dto.setListCollection(microsoftList.getListCollection().stream()
                .map(this::mapSmartList)
                .toList());
        dto.setFavouriteCollection(microsoftList.getFavouriteCollection());
        return dto;
    }

    public RowDTO mapRow(Row row) {
        RowDTO dto = new RowDTO();
        dto.setIDataList(row.getIDataList().stream()
                .map(dataToDTOMapper::map)
                .toList());
        return dto;
    }
}

