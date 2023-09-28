package main.java.gustavo.mapa.jdbc.factoryConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import main.java.gustavo.mapa.exceptions.ConnectionFactoryException;

public abstract class FactoryConnection {
    public static Connection getConnection() throws ConnectionFactoryException {
        try {
            var prop = getProp();
            return DriverManager.getConnection(prop.getProperty("database.url"), prop.getProperty("database.user"), prop.getProperty("database.password"));
        } catch (SQLException e) {
            throw new ConnectionFactoryException("Error establishing connection to the database.", e);
        } catch (IOException e) {
            throw new ConnectionFactoryException("Error loading properties file.", e);
        }
    }

    private static Properties getProp() throws IOException  {
        var prop = new Properties();

        Path path = Paths.get("src/resources/conexao.properties");
        
        try (var fileInputStream = Files.newInputStream(path)) {
            prop.load(fileInputStream);
        }
    
        return prop;
    }
}
