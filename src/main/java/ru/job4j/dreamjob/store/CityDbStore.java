package ru.job4j.dreamjob.store;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class CityDbStore {

    private final BasicDataSource pool;

    public CityDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM city")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(resultSet.getInt("id"), resultSet.getString("name")));
                }
            }
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return cities;
    }

    public City findById(int id) {
        City res = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM city WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    res =  new City(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
            log.error("SQLException", e);
        }
        return res;
    }

}
