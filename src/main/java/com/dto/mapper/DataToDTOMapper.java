package com.dto.mapper;

import com.model.datatype.*;
import com.model.datatype.Number;
import com.dto.data.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DataToDTOMapper {
    public final Map<Class<? extends IData<?>>, Function<IData<?>, Object>> mapper = new HashMap<>();

    public DataToDTOMapper() {
        mapper.put(Choice.class, data -> mapChoice((Choice) data));
        mapper.put(DateAndTime.class, data -> mapDateAndTime((DateAndTime) data));
        mapper.put(HyperLink.class, data -> mapHyperLink((HyperLink) data));
        mapper.put(Image.class, data -> mapImage((Image) data));
        mapper.put(MultipleChoice.class, data -> mapMultipleChoice((MultipleChoice) data));
        mapper.put(MultiplePerson.class, data -> mapMultiplePerson((MultiplePerson) data));
        mapper.put(Number.class, data -> mapNumber((Number) data));
        mapper.put(Person.class, data -> mapPerson((Person) data));
        mapper.put(Rating.class, data -> mapRating((Rating) data));
        mapper.put(Text.class, data -> mapText((Text) data));
        mapper.put(YesNo.class, data -> mapYesNo((YesNo) data));
    }

    public Object map(IData<?> data) {
        Function<IData<?>, Object> function = mapper.get(data.getClass());
        return function.apply(data);
    }

    public ChoiceDTO mapChoice(Choice choice) {
        ChoiceDTO dto = new ChoiceDTO();
        dto.setName(choice.getName());
        dto.setShape(choice.getShape());
        dto.setColor(choice.getColor());
        dto.setType(choice.getType());
        return dto;
    }

    public DateAndTimeDTO mapDateAndTime(DateAndTime dateAndTime) {
        DateAndTimeDTO dto = new DateAndTimeDTO();
        dto.setDate(dateAndTime.getDate());
        dto.setTime(dateAndTime.getTime());
        dto.setType(dateAndTime.getType());
        return dto;
    }

    public HyperLinkDTO mapHyperLink(HyperLink hyperLink) {
        HyperLinkDTO dto = new HyperLinkDTO();
        dto.setLink(hyperLink.getLink());
        dto.setType(hyperLink.getType());
        return dto;
    }

    public ImageDTO mapImage(Image image) {
        ImageDTO dto = new ImageDTO();
        dto.setImg(image.getImg());
        dto.setSize(image.getSize());
        dto.setType(image.getType());
        return dto;
    }

    public MultipleChoiceDTO mapMultipleChoice(MultipleChoice multipleChoice) {
        MultipleChoiceDTO dto = new MultipleChoiceDTO();
        dto.setChoices(multipleChoice.getData().stream()
                .map(this::mapChoice)
                .toList());
        dto.setType(multipleChoice.getType());
        return dto;
    }

    public MultiplePersonDTO mapMultiplePerson(MultiplePerson multiplePerson) {
        MultiplePersonDTO dto = new MultiplePersonDTO();
        dto.setPeople(multiplePerson.getData().stream()
                .map(this::mapPerson)
                .toList());
        dto.setType(multiplePerson.getType());
        return dto;
    }

    public NumberDTO mapNumber(Number number) {
        NumberDTO dto = new NumberDTO();
        dto.setNum(number.getNum());
        dto.setIcon(number.getIcon());
        dto.setType(number.getType());
        return dto;
    }

    public PersonDTO mapPerson(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setName(person.getName());
        dto.setImage(person.getImage());
        dto.setType(person.getType());
        return dto;
    }

    public RatingDTO mapRating(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setRate(rating.getRate());
        dto.setType(rating.getType());
        return dto;
    }

    public TextDTO mapText(Text text) {
        TextDTO dto = new TextDTO();
        dto.setStr(text.getStr());
        dto.setType(text.getType());
        return dto;
    }

    public YesNoDTO mapYesNo(YesNo yesNo) {
        YesNoDTO dto = new YesNoDTO();
        dto.setChosen(yesNo.isChosen());
        dto.setType(yesNo.getType());
        return dto;
    }
}

