package com.mgalicia.gendra.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class City {
    private static final Logger _logger = LogManager.getLogger(City.class);

    private int geonameid;                  //  : integer id of record in geonames database
    private String name;                    //  : name of geographical point (utf8) varchar(200)
    private String asciiname;               //  : name of geographical point in plain ascii characters, varchar(200)
    private String alternatenames;          //  : alternatenames, comma separated varchar(5000)
    private float latitude;                 //  : latitude in decimal degrees (wgs84)
    private float longitude;                //  : longitude in decimal degrees (wgs84)
    private char feature_class;             //  : see http://www.geonames.org/export/codes.html, char(1)
    private String feature_code;            //  : see http://www.geonames.org/export/codes.html, varchar(10)
    private String country_code;            //  : ISO-3166 2-letter country code, 2 characters
    private String cc2;                     //  : alternate country codes, comma separated, ISO-3166 2-letter country code, 60 characters
    private String admin1_code;             //  : fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display
    //    names of this code; varchar(20)
    private String admin2_code;              //  : code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80)
    private String admin3_code;              //  : code for third level administrative division, varchar(20)
    private String admin4_code;              //  : code for fourth level administrative division, varchar(20)
    private int population;                 //  : bigint (8 byte int)
    private int elevation;                  //  : in meters, integer
    private int dem;                        //  : digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30''
    //    (ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
    private String timezone;                //  : the timezone id (see file timeZone.txt) varchar(40)
    private Date modification_date;         // : date of last modification in yyyy-MM-dd format

    private final String FORMAT_DATE = "yyyy-MM-dd";

    public City() {
    }

    public City(String[] data)  {
        this.geonameid = Integer.parseInt(data[0]);
        this.name = data[1];
        this.asciiname = data[2];
        this.alternatenames = data[3];
        this.latitude = Float.parseFloat(data[4]);
        this.longitude = Float.parseFloat(data[5]);
        this.feature_class = data[6].charAt(0);
        this.feature_code = data[7];
        this.country_code = data[8];
        this.cc2 = data[9];
        this.admin1_code = data[10];
        this.admin2_code = data[11];
        this.admin3_code = data[12];
        this.admin4_code = data[13];

        this.population = data[14].isEmpty() ? 0 : Integer.parseInt(data[14]);
        this.elevation = data[15].isEmpty() ? 0 : Integer.parseInt(data[15]);
        this.dem = data[16].isEmpty() ? 0 : Integer.parseInt(data[16]);

        this.timezone = data[17];
        try {
            this.modification_date = new SimpleDateFormat(FORMAT_DATE).parse(data[18]);
        } catch (ParseException e) {
            _logger.info("Fecha de modificación invalida o inesistente");
            this.modification_date = null;
        }


    }


    public City(int geonameid, String name, String asciiname, String alternatenames, float latitude, float longitude, char feature_class, String feature_code, String country_code, String cc2, String admin1_code, String admin2_code, String admin3_code, String admin4_code, int population, int elevation, int dem, String timezone, Date modification_date) {
        this.geonameid = geonameid;
        this.name = name;
        this.asciiname = asciiname;
        this.alternatenames = alternatenames;
        this.latitude = latitude;
        this.longitude = longitude;
        this.feature_class = feature_class;
        this.feature_code = feature_code;
        this.country_code = country_code;
        this.cc2 = cc2;
        this.admin1_code = admin1_code;
        this.admin2_code = admin2_code;
        this.admin3_code = admin3_code;
        this.admin4_code = admin4_code;
        this.population = population;
        this.elevation = elevation;
        this.dem = dem;
        this.timezone = timezone;
        this.modification_date = modification_date;
    }

    @Override
    public String toString() {
        return "City{" +
                "geonameid=" + geonameid +
                ", name='" + name + '\'' +
                ", asciiname='" + asciiname + '\'' +
                ", alternatenames='" + alternatenames + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", feature_class=" + feature_class +
                ", feature_code='" + feature_code + '\'' +
                ", country_code='" + country_code + '\'' +
                ", cc2='" + cc2 + '\'' +
                ", admin1_code='" + admin1_code + '\'' +
                ", admin2_code='" + admin2_code + '\'' +
                ", admin3_code='" + admin3_code + '\'' +
                ", admin4_code='" + admin4_code + '\'' +
                ", population=" + population +
                ", elevation=" + elevation +
                ", dem=" + dem +
                ", timezone='" + timezone + '\'' +
                ", modification_date=" + modification_date +
                '}';
    }

    public int getGeonameid() {
        return geonameid;
    }

    public void setGeonameid(int geonameid) {
        this.geonameid = geonameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

    public void setAlternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public char getFeature_class() {
        return feature_class;
    }

    public void setFeature_class(char feature_class) {
        this.feature_class = feature_class;
    }

    public String getFeature_code() {
        return feature_code;
    }

    public void setFeature_code(String feature_code) {
        this.feature_code = feature_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1_code() {
        return admin1_code;
    }

    public void setAdmin1_code(String admin1_code) {
        this.admin1_code = admin1_code;
    }

    public String getAdmin2_code() {
        return admin2_code;
    }

    public void setAdmin2_code(String admin2_code) {
        this.admin2_code = admin2_code;
    }

    public String getAdmin3_code() {
        return admin3_code;
    }

    public void setAdmin3_code(String admin3_code) {
        this.admin3_code = admin3_code;
    }

    public String getAdmin4_code() {
        return admin4_code;
    }

    public void setAdmin4_code(String admin4_code) {
        this.admin4_code = admin4_code;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getDem() {
        return dem;
    }

    public void setDem(int dem) {
        this.dem = dem;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getModification_date() {
        return modification_date;
    }

    public void setModification_date(Date modification_date) {
        this.modification_date = modification_date;
    }
}
