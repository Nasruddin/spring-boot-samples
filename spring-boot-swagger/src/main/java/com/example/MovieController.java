package com.example;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by nasruddin on 16/10/16.
 */
@RestController
@RequestMapping("/api/movies")
@Api("/api/movies")
public class MovieController {

    private MovieService movieService;

    private MessageSource messageSource;

    @Autowired
    public MovieController(MovieService movieService, MessageSource messageSource) {
        this.movieService = movieService;
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all movies", notes = "Retrieving the collection of movies", response = MovieDTO[].class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = MovieDTO[].class)
    })
    public List<MovieDTO> findAll(){
        return movieService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDTO create(
            @ApiParam(required = true, name = "movie", value = "New movie")
            @Valid @RequestBody MovieDTO movie) {
        return movieService.create(movie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update movie", notes = "Updating an existing movie", response = MovieDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = MovieDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = MessageDTO.class),
            @ApiResponse(code = 404, message = "Not found", response = MessageDTO.class)
    })
    public MovieDTO update(
            @ApiParam(required = true, name = "id", value = "ID of the movie you want to update", defaultValue = "0")
            @PathVariable Long id,
            @ApiParam(required = true, name = "movie", value = "Updated movie")
            @Valid @RequestBody MovieDTO movie) {
        return movieService.update(id, movie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageDTO handleValidationException(MethodArgumentNotValidException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String code = ex.getBindingResult().getFieldError().getDefaultMessage();

        return new MessageDTO(messageSource.getMessage(code, null, locale));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MovieNotFoundException.class)
    public MessageDTO handleNotFoundException(MovieNotFoundException ex) {
        String[] parameters = {Long.toString(ex.getId())};
        Locale locale = LocaleContextHolder.getLocale();

        return new MessageDTO(messageSource.getMessage("exception.MovieNotFound.description", parameters, locale));
    }
}
