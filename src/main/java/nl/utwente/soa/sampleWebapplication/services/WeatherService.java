package nl.utwente.soa.sampleWebapplication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.utwente.soa.sampleWebapplication.domain.WeatherResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherService {

    // The values are retrieved from src\main\resources\application.properties
    @Value("${openweathermap.key}") private String key;
    @Value("${openweathermap.host}") private String host;


    // Get current weather with Spring Rest Template
    @Autowired private  RestTemplateBuilder restTemplateBuilder;
    public WeatherResult getCurrentWeatherWithSpringRestTemplate(String city){
        String url = host + "/data/2.5/weather?q=" + city + "&appid=" + key + "&units=metric";

        RestTemplate restTemplate = restTemplateBuilder.build();
        WeatherResult weatherResult = restTemplate.getForObject(url, WeatherResult.class);
        return weatherResult;
    }


    // Get current weather with manual HTTP request & Jackson parsing
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    public WeatherResult getCurrentWeatherByHand(String city){
        String urlString = host + "/data/2.5/weather?q=" + city + "&appid=" + key + "&units=metric";

        // Manual HTTP GET request
        StringBuffer content = new StringBuffer();
        HttpURLConnection con = null;
        try {
            // Build HTTP request
            URL url = new URL(urlString);
            con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json");

            // Transform the response InputStream into a String
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
            if(con != null) {
                con.disconnect();
            }
        }

        // Transform the String to JSON with Jackson
        WeatherResult weatherResult;
        try {
           weatherResult = jacksonObjectMapper.readValue(content.toString(), WeatherResult.class);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

        return weatherResult;
    }



}
