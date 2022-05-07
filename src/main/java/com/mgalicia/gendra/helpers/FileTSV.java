package com.mgalicia.gendra.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileTSV {
    private static final Logger _logger = LogManager.getLogger(FileTSV.class);

    public static List<String[]> getData(File file) {
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
}
