package com.example;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by nasruddin on 16/10/16.
 */
@ApiModel(value = "Movie", description = "A Movie")
public class MovieDTO {

    @ApiModelProperty(value = "The unique identifier of the given movie", readOnly = true)
    private Long id;

    @ApiModelProperty(value = "Name of the movie", required = true)
    @NotNull(message = "NotNull.movieDTO.name")
    @Size(min = 1, max = 64, message = "Size.movieDTO.name")
    private String name;

    public MovieDTO() {
    }

    public MovieDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
