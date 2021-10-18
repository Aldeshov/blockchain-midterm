package com.example.demo.controller;

import com.example.demo.entity.Coffee;
import com.example.demo.entity.CoffeeData;
import com.example.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @RequestMapping(value = "/deliver", method = RequestMethod.GET)
    public String deliver(Model model) {
        CoffeeData coffee = new CoffeeData();
        model.addAttribute("coffeeData", coffee);
        model.addAttribute("userIin", "");
        return "deliver";
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String apply(@ModelAttribute CoffeeData coffeeData, Model model) {
        Coffee coffee = new Coffee();
        coffee.setType(coffeeData.getType());
        coffee.setWeight(coffeeData.getWeight());
        coffee.setDepartureDate(convertToDate(coffeeData.getDepartureDate()));
        coffee.setDeliveryDate(convertToDate(coffeeData.getDeliveryDate()));
        coffee.setUserIin(coffeeData.getUserIin());
        coffee.setStatus("На комплектации");
        coffeeRepository.save(coffee);

        return "redirect:deliver";
    }

    Date convertToDate(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

            return dateFormat.parse(time);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status(@RequestParam String userIin, Model model) {
        Coffee coffee = coffeeRepository.findByUserIin(userIin).get();

        Date date = new Date();

        if (coffee.getDepartureDate().before(date)) {
            coffee.setStatus("В пути");
        }

        if (coffee.getDeliveryDate().before(date)) {
            coffee.setStatus("Доставлен");
        }

        Coffee result = coffeeRepository.save(coffee);

        model.addAttribute("result", result);
        return "status";
    }
}
