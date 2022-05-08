package com.mgalicia.gendra.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTSVHelper {
    private static final Logger _logger = LogManager.getLogger(FileTSVHelper.class);

    public static List<String[]> readLines(BufferedReader bufferedReader) {
        List<String[]> data = new ArrayList<>();
        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                data.add(lineItems);
            }
        } catch (Exception e) {
            _logger.error("Something went wrong");
        }
        data = data.subList(1, data.size()); // eliminando la primer línea contenedora de las cabeceras
        _logger.info("Número de registros: " + data.size());
        return data;
    }
}
