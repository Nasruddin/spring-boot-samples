package com.example;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by nasruddin on 16/10/16.
 */
@ApiModel(value = "Message", description = "A message containing more info why the operation failed")
public class MessageDTO {

    @ApiModelProperty(value = "The message itself", readOnly = true)
    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }

    public MessageDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
