package main.java.gustavo.mapa;

import javax.swing.SwingUtilities;

import main.java.gustavo.mapa.view.MainFrame;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainFrame window = new MainFrame();

            window.showFrame();
        });
    }
}
