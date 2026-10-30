package com.nb.spring.boot.learn.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nb.spring.boot.learn.dto.CourseResponse;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private DataSource dataSource;

    public void insert(String name, String author) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLConstants.CourseQueries.INSERT_QUERY)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, name);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting course", e);
        }
    }

    public CourseResponse findFirstByName(String name) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLConstants.CourseQueries.FIND_FIRST_BY_NAME)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    CourseResponse courseResponse = new CourseResponse();

                    courseResponse.setId(resultSet.getLong("id"));
                    courseResponse.setName(resultSet.getString("name"));
                    courseResponse.setAuthor(resultSet.getString("author"));

                    return courseResponse;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching course", e);
        }

        return null;
    }

}
