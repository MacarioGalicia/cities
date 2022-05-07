package com.mgalicia.gendra.controllers;

import com.mgalicia.gendra.DTO.CityDTO;
import com.mgalicia.gendra.models.City;
import com.mgalicia.gendra.services.ICityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private static final Logger _logger = LogManager.getLogger(CityController.class);

    @Autowired
    private ICityService _cityService;

    @Operation(summary = "Obtener sugerencias de ciudades")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de ciudades sugeridas",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CityDTO.class))),
                    })
    })
    @GetMapping("/suggestions")
    public ResponseEntity<?> findSuggestions(@RequestParam() String q, @RequestParam(required = false, defaultValue = "0") float latitude, @RequestParam(required = false, defaultValue = "0") float longitude) {
        _logger.info("Petición de sugerencia de ciudades");
        Map<String, Object> response = new HashMap<>();
        response.put("suggestions", _cityService.findBySuggestion(q, latitude, longitude));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
