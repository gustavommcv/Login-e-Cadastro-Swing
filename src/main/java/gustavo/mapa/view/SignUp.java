package main.java.gustavo.mapa.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.gustavo.mapa.jdbc.UserJDBCManager;
import main.java.gustavo.mapa.models.User;

public final class SignUp extends JFrame {
    
    private JPanel panelForm;

    private JButton saveButton;
   
    private JLabel labelName;
    private JTextField textFieldName;

    private JLabel labelLogin;
    private JTextField textFieldLogin;

    private JLabel labelPassword;
    private JTextField textFieldPassword;

    private JLabel labelEmail;
    private JTextField textFieldEmail;
    

    public SignUp() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initialize();
        events();
    }

    private void saveButtonClick(ActionEvent ev) {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        String name = textFieldName.getText();
        String email = textFieldEmail.getText();

        User user = new User(name, login, password, email);

        if (user.isValid()) {
            if (UserJDBCManager.registerUser(user)) {
                System.out.println("Cadastro:\n" + user.toString());
                JOptionPane.showMessageDialog(this, "Cadastro efetuado com sucesso");
                hideFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Cadastro não realizado");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario invalido!");
        }
    }

    private void events() {
        saveButton.addActionListener(this::saveButtonClick);
    }

    public void initialize() {
        setTitle("Cadastro");
        setSize(380, 210);

        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(getPanelForm(), BorderLayout.CENTER);
    }

    public void showFrame() {
        setVisible(true);
    }

    public void hideFrame() {
        setVisible(false);
    }

    public JPanel getPanelForm() {
        if (panelForm == null) {
            panelForm = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10); // Margins
            
            labelLogin = new JLabel("Login");
            textFieldLogin = new JTextField(20);

            labelPassword = new JLabel("Senha");
            textFieldPassword = new JTextField(20);

            labelName = new JLabel("Nome");
            textFieldName = new JTextField(20);

            labelEmail = new JLabel("Email");
            textFieldEmail = new JTextField(20);

            saveButton = new JButton("Salvar");
            
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
            
            // Add label and name text field
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.EAST;
            panelForm.add(labelName, gbc);
            gbc.anchor = GridBagConstraints.WEST;
            panelForm.add(textFieldName, gbc);
            
            // Add email label and text field
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.EAST;
            panelForm.add(labelEmail, gbc);
            gbc.anchor = GridBagConstraints.WEST;
            panelForm.add(textFieldEmail, gbc);
            
            // Add save button
            gbc.gridwidth = 2;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.CENTER;
            panelForm.add(saveButton, gbc);

        }

        return panelForm;
    }
}
