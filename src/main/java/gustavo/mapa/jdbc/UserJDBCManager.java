package main.java.gustavo.mapa.jdbc;

import main.java.gustavo.mapa.jdbc.factoryConnection.FactoryConnection;
import main.java.gustavo.mapa.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public abstract class UserJDBCManager {

    private static final String SELECT_QUERY = "SELECT id, nome, login, senha, email FROM usuario WHERE login = ? AND senha = ?";
    
    private static final String INSERT_QUERY = "INSERT INTO usuario (nome, login, senha, email) VALUES (?, ?, ?, ?)";

    public static User authenticate(String login, String password) {
        try (Connection connection = FactoryConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nome");
                String userLogin = resultSet.getString("login");
                String userPassword = resultSet.getString("senha");
                String email = resultSet.getString("email");

                User user = new User(name, userLogin, userPassword, email);
                user.setId(id);

                return user;
            }

            return null;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a autenticação");
            throw new RuntimeException("An error occurred while authenticating.", e);
        }
    }

    public static boolean registerUser(User user) {
        try (Connection connection = FactoryConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao registrar um novo usuário.");
            throw new RuntimeException("An error occurred while registering a new user.", e);
        }
    }
}
