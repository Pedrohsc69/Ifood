package Entity;

import javax.swing.*;
import java.awt.*;

public class Status_Pedido {

    JProgressBar progressBar;
    private JFrame frame;
    private JButton voltarMenu;
    private JButton historicoPedidos;
    private Menu_Inicial menu_inicial;

    Status_Pedido(Menu_Inicial menu_inicial) {
        this.menu_inicial = menu_inicial;


        frame = new JFrame("Status do Pedido");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);

        voltarMenu = new JButton("Voltar ao Menu Inicial");
        voltarMenu.addActionListener(e -> {

            frame.dispose();
            menu_inicial.Exibir_Menu();

        });

        historicoPedidos = new JButton("Histórico de pedidos");
        historicoPedidos.addActionListener(e -> {

            frame.dispose();
            Historico_Pedido.exibirHistoricoPedido();
        });

        frame.setVisible(true);
    }

    public void atualizarStatus(String status, int progresso){
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(progresso);
            progressBar.setString(status);
        });

        if (progresso == 100){
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(voltarMenu);
            buttonPanel.add(historicoPedidos);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.revalidate();
            frame.repaint();
        }

    }

}
