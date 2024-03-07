package org.example;

import dao.ClientService;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class MigrationMain {
    public static void main(String[] args) throws SQLException {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./task1", "", "")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();


    }
}
