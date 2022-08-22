package dk.figge.messages.broker.entity;

import io.quarkus.test.common.QuarkusTestResourceConfigurableLifecycleManager;
import org.testcontainers.containers.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

public class MySqlTestResource implements QuarkusTestResourceConfigurableLifecycleManager {
    private MySQLContainer mySQLContainer;

    @Override
    public Map<String, String> start() {
        this.mySQLContainer = new MySQLContainer<>("mysql:8.0.21");
        this.mySQLContainer.start();

        final var configuration = new HashMap<String, String>();
        configuration.put("quarkus.datasource.reactive.url", this.mySQLContainer.getJdbcUrl());
        configuration.put("quarkus.datasource.username", this.mySQLContainer.getUsername());
        configuration.put("quarkus.datasource.password", this.mySQLContainer.getPassword());
        return configuration;
    }

    @Override
    public void stop() {
        this.mySQLContainer.stop();
        this.mySQLContainer.close();
    }
}
