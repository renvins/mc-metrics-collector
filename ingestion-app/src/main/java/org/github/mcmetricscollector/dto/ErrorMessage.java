package org.github.mcmetricscollector.dto;

import lombok.Data;

@Data
public class ErrorMessage {

    private String description;
    private int errorCode;
}
