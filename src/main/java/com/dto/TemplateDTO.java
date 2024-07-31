package com.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDTO {
    private String name;
    private List<Object> columns;
}
