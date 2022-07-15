package ru.job4j.dreamjob.store;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;
import java.sql.*;
import java.util.Optional;

@Repository
@ThreadSafe
@Slf4j
public class UserDbStore {

    private BasicDataSource pool;

    public UserDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        Optional<User> res = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO users(email, password) values (?, ?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            res = Optional.of(user);
            statement.execute();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return res;
    }



}
