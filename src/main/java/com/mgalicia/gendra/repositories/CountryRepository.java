package com.mgalicia.gendra.repositories;


import com.mgalicia.gendra.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class CountryRepository implements ICountryRepository {

    private static final Logger _logger = LogManager.getLogger(CountryRepository.class);

    @Value(value = "${db.cities}")
    private String _db;

    private List<City> cities = new ArrayList<>();
    private File file;

    @PostConstruct
    public void init() throws FileNotFoundException, NullPointerException {
        _logger.info("Inicializando base de datos");
        _logger.info("Ruta del archivo del repositorio: " + _db);
        _logger.info("Inicializando base de datos");
        _logger.info(String.format("Ruta del archivo del repositorio: %s", _db));
        _logger.info("Cargando repositorio");
        File file = ResourceUtils.getFile("src\\main\\resources\\data\\cities_canada-usa.tsv");
        load(file);
    }


    private List<String[]> getData(File file) {
        List<String[]> data = new ArrayList<>(); //initializing a new ArrayList out of String[]'s
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                data.add(lineItems); //adding the splitted line array to the ArrayList
            }
        } catch (Exception e) {
            _logger.error("Something went wrong");
        }
        data = data.subList(1, data.size()); // delete headers
        _logger.info("Número de registros: " + data.size());
        return data;
    }

    private void load(File file) {
        List<String[]> data = getData(file);
        data.forEach(d -> {
            City city = new City(d);
            cities.add(city);
            _logger.info(city);
        });
    }
}
