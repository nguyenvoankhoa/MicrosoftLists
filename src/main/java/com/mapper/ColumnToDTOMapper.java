package com.mapper;

import com.model.column.*;
import com.model.column.IColumn;
import com.util.Common;
import com.dto.column.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ColumnToDTOMapper {

    private final DataToDTOMapper dataToDTOMapper = new DataToDTOMapper();

    public final Map<Class<? extends IColumn<?>>, Function<IColumn<?>, Object>> mapper = new HashMap<>();

    public ColumnToDTOMapper() {
        mapper.put(HyperLinkColumn.class, column -> mapHyperLinkColumn((HyperLinkColumn) column));
        mapper.put(ImageColumn.class, column -> mapImageColumn((ImageColumn) column));
        mapper.put(NumberColumn.class, column -> mapNumberColumn((NumberColumn) column));
        mapper.put(PersonColumn.class, column -> mapPersonColumn((PersonColumn) column));
        mapper.put(RatingColumn.class, column -> mapRatingColumn((RatingColumn) column));
        mapper.put(TextColumn.class, column -> mapTextColumn((TextColumn) column));
        mapper.put(TimeColumn.class, column -> mapTimeColumn((TimeColumn) column));
        mapper.put(YesNoColumn.class, column -> mapYesNoColumn((YesNoColumn) column));
        mapper.put(ChoiceColumn.class, column -> mapChoiceColumn((ChoiceColumn) column));
    }

    public Object map(IColumn<?> column) {
        Function<IColumn<?>, Object> function = mapper.get(column.getClass());
        Common.checkNonExist(function);
        return function.apply(column);
    }

    public ChoiceColumnDTO mapChoiceColumn(ChoiceColumn choiceColumn) {
        ChoiceColumnDTO dto = new ChoiceColumnDTO();
        dto.setName(choiceColumn.getName());
        dto.setChoices(choiceColumn.getChoices().stream()
                .map(dataToDTOMapper::mapChoice)
                .toList());
        dto.setType(choiceColumn.getType());
        dto.setRequire(choiceColumn.isRequire());
        return dto;
    }


    public HyperLinkColumnDTO mapHyperLinkColumn(HyperLinkColumn column) {
        HyperLinkColumnDTO dto = new HyperLinkColumnDTO();
        dto.setName(column.getName());
        dto.setHyperLink(dataToDTOMapper.mapHyperLink(column.getDefaultData()));
        dto.setType(column.getColumnType());
        dto.setRequire(column.isRequire());
        return dto;
    }

    public ImageColumnDTO mapImageColumn(ImageColumn column) {
        ImageColumnDTO dto = new ImageColumnDTO();
        dto.setName(column.getName());
        dto.setImage(dataToDTOMapper.mapImage(column.getDefaultData()));
        dto.setType(column.getColumnType());
        dto.setRequire(column.isRequire());
        return dto;
    }

    public NumberColumnDTO mapNumberColumn(NumberColumn column) {
        NumberColumnDTO dto = new NumberColumnDTO();
        dto.setNumber(dataToDTOMapper.mapNumber(column.getDefaultData()));
        dto.setName(column.getName());
        dto.setType(column.getColumnType());
        dto.setRequire(column.isRequire());
        dto.setMinVal(column.getMinVal());
        dto.setMaxVal(column.getMaxVal());
        return dto;
    }

    public PersonColumnDTO mapPersonColumn(PersonColumn column) {
        PersonColumnDTO dto = new PersonColumnDTO();
        dto.setName(column.getName());
        dto.setType(column.getColumnType());
        dto.setRequire(column.isRequire());
        return dto;
    }

    public RatingColumnDTO mapRatingColumn(RatingColumn column) {
        RatingColumnDTO dto = new RatingColumnDTO();
        dto.setName(column.getName());
        dto.setRating(dataToDTOMapper.mapRating(column.getDefaultData()));
        dto.setType(column.getColumnType());
        dto.setMaxRate(column.getMaxRate());
        dto.setMinRate(column.getMinRate());
        dto.setRequire(column.isRequire());
        return dto;
    }

    public TextColumnDTO mapTextColumn(TextColumn column) {
        TextColumnDTO dto = new TextColumnDTO();
        dto.setName(column.getName());
        dto.setText(dataToDTOMapper.mapText(column.getDefaultData()));
        dto.setType(column.getColumnType());
        dto.setMaxLength(column.getMaxLength());
        dto.setRequire(column.isRequire());
        return dto;
    }

    public TimeColumnDTO mapTimeColumn(TimeColumn column) {
        TimeColumnDTO dto = new TimeColumnDTO();
        dto.setName(column.getName());
        dto.setDateAndTime(dataToDTOMapper.mapDateAndTime(column.getDefaultData()));
        dto.setType(column.getColumnType());
        dto.setRequire(column.isRequire());
        return dto;
    }

    public YesNoColumnDTO mapYesNoColumn(YesNoColumn column) {
        YesNoColumnDTO dto = new YesNoColumnDTO();
        dto.setName(column.getName());
        dto.setData(dataToDTOMapper.mapYesNo(column.getDefaultData()));
        dto.setType(column.getColumnType());
        dto.setRequire(column.isRequire());
        return dto;
    }
}

