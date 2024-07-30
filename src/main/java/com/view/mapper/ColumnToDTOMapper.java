package com.view.mapper;

import com.model.column.*;
import com.model.column.IColumn;
import com.service.Common;
import com.view.column.*;

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
        dto.setMultiSelect(choiceColumn.isMultiSelect());
        dto.setType(choiceColumn.getType());
        return dto;
    }

    public HyperLinkColumnDTO mapHyperLinkColumn(HyperLinkColumn column) {
        HyperLinkColumnDTO dto = new HyperLinkColumnDTO();
        dto.setName(column.getName());
        dto.setHyperLink(column.getDefaultData());
        dto.setType(column.getColumnType());
        return dto;
    }

    public ImageColumnDTO mapImageColumn(ImageColumn column) {
        ImageColumnDTO dto = new ImageColumnDTO();
        dto.setName(column.getName());
        dto.setImage(column.getDefaultData());
        dto.setType(column.getColumnType());
        return dto;
    }

    public NumberColumnDTO mapNumberColumn(NumberColumn column) {
        NumberColumnDTO dto = new NumberColumnDTO();
        dto.setNumber(column.getDefaultData());
        dto.setName(column.getName());
        dto.setType(column.getColumnType());
        return dto;
    }

    public PersonColumnDTO mapPersonColumn(PersonColumn column) {
        PersonColumnDTO dto = new PersonColumnDTO();
        dto.setName(column.getName());
        dto.setPeople(column.getPeople().stream()
                .map(dataToDTOMapper::mapPerson)
                .toList());
        dto.setMultiSelect(column.isMultiSelect());
        dto.setType(column.getColumnType());
        return dto;
    }

    public RatingColumnDTO mapRatingColumn(RatingColumn column) {
        RatingColumnDTO dto = new RatingColumnDTO();
        dto.setName(column.getName());
        dto.setRating(column.getDefaultData());
        dto.setType(column.getColumnType());
        return dto;
    }

    public TextColumnDTO mapTextColumn(TextColumn column) {
        TextColumnDTO dto = new TextColumnDTO();
        dto.setName(column.getName());
        dto.setText(column.getDefaultData());
        dto.setType(column.getColumnType());
        return dto;
    }

    public TimeColumnDTO mapTimeColumn(TimeColumn column) {
        TimeColumnDTO dto = new TimeColumnDTO();
        dto.setName(column.getName());
        dto.setDateAndTime(column.getDefaultData());
        dto.setType(column.getColumnType());
        return dto;
    }

    public YesNoColumnDTO mapYesNoColumn(YesNoColumn column) {
        YesNoColumnDTO dto = new YesNoColumnDTO();
        dto.setName(column.getName());
        dto.setData(column.getDefaultData());
        dto.setType(column.getColumnType());
        return dto;
    }
}

