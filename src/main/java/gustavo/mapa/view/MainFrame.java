package main.java.gustavo.mapa.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.java.gustavo.mapa.jdbc.UserJDBCManager;
import main.java.gustavo.mapa.models.User;

public final class MainFrame extends JFrame {
    
    private JPanel panelForm;
    private JPanel panelFooter;

    private JButton signInButton;
    private JButton signUpButton;

    private JLabel labelLogin;
    private JTextField textFieldLogin;

    private JLabel labelPassword;
    private JPasswordField textFieldPassword;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initialize();
        events();
    }

    public void initialize() {
        setTitle("Login");
        setSize(380, 210);

        getContentPane().setLayout(new BorderLayout());
        
        getContentPane().add(getPanelForm(), BorderLayout.CENTER);
        getContentPane().add(getPanelFooter(), BorderLayout.SOUTH);
    }

    public void showFrame() {
        setVisible(true);
    }

    public void hideFrame() {
        setVisible(false);
    }

    private void signInButtonClick(ActionEvent ev) {
        String login = textFieldLogin.getText();
        char[] passwordChars = textFieldPassword.getPassword();

        String password = new String(passwordChars);

        User user = UserJDBCManager.authenticate(login, password);

        if (user != null) {
            System.out.println("Login:\n" + user.toString());
            JOptionPane.showMessageDialog(this, "Acesso Autorizado");
        } else {
            JOptionPane.showMessageDialog(this, "Acesso Negado");
        }
    }

    private void signUpButtonClick(ActionEvent ev) {
        SignUp signUpWindow = new SignUp();

        signUpWindow.showFrame();
    }

    private void events() {
        signInButton.addActionListener(this::signInButtonClick);
        signUpButton.addActionListener(this::signUpButtonClick);
    }

    public JPanel getPanelForm() {
        if (panelForm == null) {
            panelForm = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10); // Margins
            
            labelLogin = new JLabel("Login");
            textFieldLogin = new JTextField(20); // Specify columns for width
            
            labelPassword = new JLabel("Senha");
            textFieldPassword = new JPasswordField(20); // Specify columns for width

            signInButton = new JButton("Entrar");
            
            // Add login label and text field
            gbc.anchor = GridBagConstraints.EAST;
            panelForm.add(labelLogin, gbc);
            gbc.anchor = GridBagConstraints.WEST;
            panelForm.add(textFieldLogin, gbc);
            
            // Add label and password text field
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.EAST;
            panelForm.add(labelPassword, gbc);
            gbc.anchor = GridBagConstraints.WEST;
            panelForm.add(textFieldPassword, gbc);
            
            // Add login button
            gbc.gridwidth = 2;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            panelForm.add(signInButton, gbc);
        }

        return panelForm;
    }

    public JPanel getPanelFooter() {
        if (panelFooter == null) {
            panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER));

            signUpButton = new JButton("Cadastrar novo Usuário");

            panelFooter.add(signUpButton);
        }

        return panelFooter;
    }
}
