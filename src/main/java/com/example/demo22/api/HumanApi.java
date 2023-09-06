package com.example.demo22.api;

import com.example.demo22.model.Human;
import com.example.demo22.model.People;
import com.example.demo22.exception.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Profile("api")
@RequestMapping("api/v1/humans")
public class HumanApi {
    int maxId = 0;
    private final People people;

    @Autowired
    public HumanApi(People people) {
        this.people = people;
        System.out.println("Конструктор класса HumanApiBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Метод init класса HumanApiBean PostConstruct");
    }

    @GetMapping("filters")
    public Human getByFilters(@RequestParam(required = false) String name) {
        return people.readHuman().stream()
                .filter(h -> Objects.equals(h.getName(), name))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("human with name " + name + " not found"));
    }

    @GetMapping
    public List<Human> read() {
        return people.readHuman();
    }

    @PostMapping
    public Human add(@RequestBody Human human) {
        people.addHuman(human);
        return human;
    }

    @DeleteMapping("{name}")
    public void delete(@PathVariable String name) {
        people.deleteHuman(name);
    }
}