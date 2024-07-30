package com.dto;

import com.model.Template;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MicrosoftListDTO {
    List<Template> templates;
    List<SmartListDTO> listCollection;
    List<String> favouriteCollection;

}
