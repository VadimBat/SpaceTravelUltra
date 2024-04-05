package com.goit.storage;

import org.flywaydb.core.Flyway;

/**
 * Database initialization service provides migrations to database using flyway and connection URL.
 */
public class DbInitService {

    public void initDb(String connectionUrl) {

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }
}
