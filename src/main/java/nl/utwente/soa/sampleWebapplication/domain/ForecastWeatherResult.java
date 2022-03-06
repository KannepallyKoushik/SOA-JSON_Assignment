package nl.utwente.soa.sampleWebapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;
import java.util.Map;

// JsonIgnoreProperties is a property from Jackson
// It says if the json contains properties not existing in our class we ignore them.
@JsonIgnoreProperties(ignoreUnknown = true)

// The following two annotations are from lombok (https://projectlombok.org/)
// They automatically generate Getters and Setters for the objects.
@Getter
@Setter
public class ForecastWeatherResult {
    private Integer cod;
    private String message;
    private Integer cnt;
    private List<Forecast> list;
    private City city;

    @Getter @Setter
    public static class City {
        private int id;
        @JsonProperty("name")
        private String city;
        private Coord coord;
        private String country;
        private  Integer population;
        private Integer timezone;
        private Integer sunrise;
        private Integer sunset;
    }

    @Getter @Setter
    public static class Coord {
        private double lat;
        private double lon;
    }

    @Getter @Setter
    public static class Forecast {
        private Integer dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private Integer visibility;
        private Integer pop;
        private Sys sys;
        private String dt_txt;
    }

    @Getter @Setter
    public static class Main {
        private Double temp;
        private Double feels_like;
        private Double temp_min;
        private Double temp_max;
        private Double pressure;
        private Double humidity;
        private Double sea_level;
        private Double grnd_level;
    }

    @Getter @Setter
    public static class Weather {
        private Long id;
        private String main;
        private String description;
        private String icon;
    }

    @Getter @Setter
    public static class Clouds {
        private int all;
    }

    @Getter @Setter
    public static class Wind {
        private double speed;
        private int deg;
        private Double gust;
    }

    @Getter @Setter
    public static class Sys {
        private String pod;
    }

}
