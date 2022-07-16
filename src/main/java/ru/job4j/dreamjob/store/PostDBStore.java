package ru.job4j.dreamjob.store;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class PostDBStore {

    private final BasicDataSource pool;

    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM post")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    posts.add(createPost(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return posts;
    }

    public Post add(Post post) {
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO post(name, description, created, visible, city) VALUES (?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getName());
            statement.setString(2, post.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
            statement.setBoolean(4, post.isVisible());
            statement.setInt(5, post.getIdCity());
            statement.execute();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return post;
    }

    public void delete(int id) {
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM post where id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
    }

    public void update(Post post) {
        try (Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE post set name = ?, description = ? where id = ?")) {
            statement.setString(1, post.getName());
            statement.setString(2, post.getDescription());
            statement.setInt(3, post.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
    }

    public Post findById(int id) {
        Post res = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    res =  createPost(it);
                }
            }
        } catch (Exception e) {
            log.error("SQLException", e);
        }
        return res;
    }


    private Post createPost(ResultSet resultSet) throws SQLException {
        return new Post(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getTimestamp("created").toLocalDateTime(),
                resultSet.getBoolean("visible"),
                resultSet.getInt("city"));

    }

}
