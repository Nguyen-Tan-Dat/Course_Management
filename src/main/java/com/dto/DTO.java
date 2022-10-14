package com.dto;

import java.util.Vector;

public interface DTO {
    DTO getDTO(String[] values);

    String[] getData();

    Vector<String> toTable();
}
