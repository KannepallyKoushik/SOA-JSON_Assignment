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
public class WeatherResult {

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Map<String, Double> clouds;
    private Volume rain;
    private Volume snow;
    private Instant dt;
    private Sys sys;
    private Long timezone;
    private Long id;

    // This property is called name in the JSON, but the variable is city
    @JsonProperty("name")
    private String city;
    private Integer cod;


    @Getter @Setter
    public static class Coord {
        private double lat;
        private double lon;
    }

    @Getter @Setter
    public static class Weather {
        private Long id;
        private String main;
        private String description;
        private String icon;
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
    public static class Wind {
        private double speed;
        private int deg;
    }

    @Getter @Setter
    public  static class Volume {
        // These properties start with a number in the JSON,
        // but java doesnt allow a variable to start with a number
        @JsonProperty("1h")
        private Double volume1h;

        @JsonProperty("2h")
        private Double volume2h;
    }

    @Getter @Setter
    public static class Sys {
        private Integer type;
        private Long id;
        private String message;
        private String country;
        private Instant sunrise;
        private Instant sunset;
    }


}
