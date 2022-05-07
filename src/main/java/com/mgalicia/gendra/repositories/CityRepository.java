package com.mgalicia.gendra.repositories;

import com.mgalicia.gendra.helpers.FileTSV;
import com.mgalicia.gendra.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class CityRepository implements ICityRepository {

    private static final Logger _logger = LogManager.getLogger(CityRepository.class);

    @Value(value = "${db.cities}")
    private String _db;

    private List<City> _cities = new ArrayList<>();
    private File file;

    @PostConstruct
    public void init() throws FileNotFoundException, NullPointerException {
        _logger.info("Inicializando base de datos");
        _logger.info("Cargando repositorio");

        _logger.info(String.format("Ruta del archivo del repositorio: %s", _db));
        File file = ResourceUtils.getFile(_db);
        load(file);
    }

    private void load(File file) {
        List<String[]> data = FileTSV.getData(file);
        data.forEach(d -> _cities.add(new City(d)));
        _logger.info("Base de datos en memoria cargada");
    }

    @Override
    public List<City> findByQueryName(String queryName) {
        _logger.info("Realizando busqueda de coincidencias por nombre de la ciudad");
        _logger.info("Patron de busqueda: " + queryName);
        List<City> cities = _cities.stream().filter(c ->
                c.getName().contains(queryName)).collect(Collectors.toList());
        _logger.info("Busqueda terminada, número de coincidencias encontradas: " + cities.size());
        return cities;
    }

}
