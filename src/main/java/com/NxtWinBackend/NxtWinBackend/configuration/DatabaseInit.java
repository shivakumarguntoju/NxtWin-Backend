package com.NxtWinBackend.NxtWinBackend.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@Component
public class DatabaseInit {

    @Bean
    CommandLineRunner createDatabaseIfNotExists() {
        return args -> {
            String dbName = "prediction_market";
            String url = "jdbc:postgresql://localhost:5432/";
            String username = "postgres";
            String password = "root";

            try (Connection conn = DriverManager.getConnection(url + "postgres", username, password)) {
                ResultSet rs = conn.createStatement()
                        .executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'");
                if (!rs.next()) {
                    conn.createStatement().executeUpdate("CREATE DATABASE " + dbName);
                    System.out.println("✅ Database '" + dbName + "' created.");
                } else {
                    System.out.println("ℹ️ Database '" + dbName + "' already exists.");
                }
            }
        };
    }
}
