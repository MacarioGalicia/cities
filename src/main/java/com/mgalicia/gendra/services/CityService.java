package com.mgalicia.gendra.services;

import com.mgalicia.gendra.DTO.CityDTO;
import com.mgalicia.gendra.models.City;
import com.mgalicia.gendra.repositories.ICityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService implements ICityService {

    private static final Logger _logger = LogManager.getLogger(CityService.class);

    @Autowired
    private ICityRepository _cityRepository;

    @Autowired
    private ModelMapper _modelMapper;

    @Override
    public List<City> findByQueryName(String queryName) {
        _logger.info("Obtener ciudades por coincidencia ante su nombre");
        return _cityRepository.findByQueryName(queryName);
    }

    @Override
    public List<CityDTO> findBySuggestion(String q, float latitude, float longitude) {

        List<CityDTO> cityDTOS = findByQueryName(q).stream().map(c -> {
            double score = 1;
            if (!c.getName().startsWith(q))
                score = 0.5;

            if (latitude != 0 && longitude != 0)
                score = score - factorByCoordinates(latitude, c.getLatitude(), longitude, c.getLongitude());

            CityDTO cityDTO = _modelMapper.map(c, CityDTO.class);
            cityDTO.setScore(score);
            return cityDTO;
        }).collect(Collectors.toList());

        // Ordenamiento de las ciudades por el atributo de puntuación 'scored'
        cityDTOS.sort(Comparator.comparing(CityDTO::getScore).reversed());
        _logger.info("Listado de sugerencias de ciudades obtenido");
        return cityDTOS;
    }

    private double factorByCoordinates(float latitude, float originLatitude, float longitude, float originLongitude) {

        double lat = Math.abs(originLatitude > latitude ? originLatitude - latitude : latitude - originLatitude);
        double lon = Math.abs(originLongitude > longitude ? originLongitude - longitude : originLongitude - longitude);

        if (lat < 3 || lon < 3)
            return 0.1;
        if (lat < 5 || lon < 5)
            return 0.2;
        if (lat < 7 || lon < 7)
            return 0.3;
        if (lat < 9 || lon < 9)
            return 0.4;
        return 0;
    }
}
