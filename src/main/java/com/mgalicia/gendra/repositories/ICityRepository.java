package com.mgalicia.gendra.repositories;

import com.mgalicia.gendra.models.City;

import java.util.List;

public interface ICityRepository {

    /**
     * Realiza busqueda de las ciudades por coincidencia en su nombre
     * @param queryName Coincidencia del nombre a buscar
     * @return Listado de ciudades sugeridas
     */
    List<City> findByQueryName(String queryName);
}
