package com.view.mapper;

import com.model.datatype.*;
import com.model.datatype.Number;
import com.view.data.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DataToDTOMapper {
    private DataToDTOMapper(){}

    private static final Map<Class<? extends IData<?>>, Function<IData<?>, Object>> mapper = new HashMap<>();

    static {
        mapper.put(Choice.class, data -> new ChoiceDTO((Choice) data));
        mapper.put(DateAndTime.class, data -> new DateAndTimeDTO((DateAndTime) data));
        mapper.put(HyperLink.class, data -> new HyperLinkDTO((HyperLink) data));
        mapper.put(Image.class, data -> new ImageDTO((Image) data));
        mapper.put(MultipleChoice.class, data -> new MultipleChoiceDTO((MultipleChoice) data));
        mapper.put(MultiplePerson.class, data -> new MultiplePersonDTO((MultiplePerson) data));
        mapper.put(Number.class, data -> new NumberDTO((Number) data));
        mapper.put(Person.class, data -> new PersonDTO((Person) data));
        mapper.put(Rating.class, data -> new RatingDTO((Rating) data));
        mapper.put(Text.class, data -> new TextDTO((Text) data));
        mapper.put(YesNo.class, data -> new YesNoDTO((YesNo) data));
    }

    public static Object map(IData<?> data) {
        Function<IData<?>, Object> function = mapper.get(data.getClass());
        return function.apply(data);
    }
}

