package Entity;

import javax.swing.*;
import java.awt.*;

public class Status_Pedido {

    JProgressBar progressBar;
    private JFrame frame;

    Status_Pedido() {
        frame = new JFrame("Status do Pedido");
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void atualizarStatus(String status, int progresso){
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(progresso);
            progressBar.setString(status);
        });
    }

}
