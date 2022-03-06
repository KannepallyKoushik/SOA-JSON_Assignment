package nl.utwente.soa.sampleWebapplication.web;

import nl.utwente.soa.sampleWebapplication.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired private WeatherService weatherService;

    @GetMapping("/")
    public String showWeather(Model model) {

        model.addAttribute("weather", weatherService.getCurrentWeatherWithSpringRestTemplate("Amsterdam,NL"));

        return "show-weather";
    }
}
