package com.config;

import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.view.MicrosoftListDTO;
import com.view.RowDTO;
import com.view.SmartListDTO;
import com.view.mapper.ColumnToDTOMapper;
import com.view.mapper.DataToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

    static ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(SmartList.class, SmartListDTO.class)
                .addMappings(mapper -> {
                    mapper.map(SmartList::getName, SmartListDTO::setName);
                    mapper.map(src -> src.getRows().stream()
                                    .map(row -> modelMapper.map(row, RowDTO.class))
                                    .toList(),
                            SmartListDTO::setRows);
                    mapper.map(src -> src.getColumns().stream()
                                    .map(column -> modelMapper.map(column, ColumnToDTOMapper.map(column).getClass()))
                                    .toList(),
                            SmartListDTO::setColumns);
                });

        modelMapper.createTypeMap(Row.class, RowDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getIDataList().stream()
                                .map(data -> modelMapper.map(data, DataToDTOMapper.map(data).getClass()))
                                .toList(),
                        RowDTO::setIDataList));

        modelMapper.createTypeMap(MicrosoftList.class, MicrosoftListDTO.class)
                .addMappings(mapper -> {
                    mapper.map(MicrosoftList::getTemplates, MicrosoftListDTO::setTemplates);
                    mapper.map(src -> src.getListCollection().stream()
                                    .map(smartList -> modelMapper.map(smartList, SmartListDTO.class))
                                    .toList(),
                            MicrosoftListDTO::setListCollection);
                    mapper.map(src -> src.getFavouriteCollection().stream()
                                    .map(smartList -> modelMapper.map(smartList, SmartListDTO.class))
                                    .toList(),
                            MicrosoftListDTO::setFavouriteCollection);
                });
        return modelMapper;
    }
}

