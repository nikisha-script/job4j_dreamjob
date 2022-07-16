package ru.job4j.dreamjob.store;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class CandidateDbStore {

    private BasicDataSource pool;

    public CandidateDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Candidate add(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO candidate(name, surname, description, date_of_birth, img, visible) "
                                     + "values (?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getSurname());
            statement.setString(3, candidate.getDescription());
            statement.setTimestamp(4, Timestamp.valueOf(candidate.getDateOfBirth()));
            statement.setBytes(5, candidate.getPhoto());
            statement.setBoolean(6, candidate.isVisible());
            statement.execute();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return candidate;
    }

    public void delete(int id) {
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from candidate where id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
    }

    public void update(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE candidate set name = ?, surname = ?, description = ? where id = ?")) {
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getSurname());
            statement.setString(3, candidate.getDescription());
            statement.setInt(4, candidate.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
    }

    public List<Candidate> findAllCandidate() {
        List<Candidate> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM candidate");
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(new Candidate(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("date_of_birth").toLocalDateTime(),
                        resultSet.getBytes("img"),
                        resultSet.getBoolean("visible")));
            }
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return result;
    }

    public Candidate findById(int id) {
        Candidate res = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(it.getInt("id"),
                            it.getString("name"),
                            it.getString("surname"),
                            it.getString("description"),
                            it.getTimestamp("date_of_birth").toLocalDateTime(),
                            it.getBytes("img"),
                            it.getBoolean("visible"));
                }
            }
        } catch (Exception e) {
            log.error("SQLException", e);
        }
        return res;
    }
}
