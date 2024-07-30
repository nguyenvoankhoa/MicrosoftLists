package com.dto.column;

import com.dto.data.PersonDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonColumnDTO extends BaseColumDTO{
    List<PersonDTO> people;
    boolean isMultiSelect;
}
