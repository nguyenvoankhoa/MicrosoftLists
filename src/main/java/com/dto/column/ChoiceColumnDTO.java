package com.dto.column;

import com.dto.data.ChoiceDTO;
import lombok.Data;

import java.util.List;
@Data
public class ChoiceColumnDTO extends BaseColumDTO{
    List<ChoiceDTO> choices;
}
