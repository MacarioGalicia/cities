package com.mgalicia.gendra.services;

import com.mgalicia.gendra.DTO.CityDTO;
import com.mgalicia.gendra.models.City;

import java.util.List;

public interface ICityService {

    /**
     * Obtener listado de ciudades  por coincidencia en su nombre
     * @param queryName Coincidencia del nombre a buscar
     * @return Listado de ciudades
     */
    List<City> findByQueryName(String queryName);

    /**
     * Obtener listaoo de ciudades sugeridas por coincidencias en su nombre, latitud y longitud
     * @param q Coincidencia del nombre a buscar
     * @param latitude Cordenada geografica latitud
     * @param longitude Cordenada geografica longitud
     * @return
     */
    List<CityDTO> findBySuggestion(String q, float latitude, float longitude);
}
