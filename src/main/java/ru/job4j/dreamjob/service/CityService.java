package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.store.CityDbStore;

import java.util.List;


@Service
@ThreadSafe
public class CityService {

    private final CityDbStore store;

    public CityService(CityDbStore store) {
        this.store = store;
    }

    public List<City> getAllCities() {
        return store.findAll();
    }

    public City findById(int id) {
        return store.findById(id);
    }
}
