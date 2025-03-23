package com.sebaorrego.foringa.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ResponseDTO {
    private boolean success;
    private String message;
    private Object data;
}
