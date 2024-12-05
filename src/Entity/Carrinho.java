package Entity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemPedido> itens;
    private double valorTotal;

    public Carrinho() {
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        valorTotal = valorTotal + item.getPrecoTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void ExibirCarrinho(Menu_Inicial menu_inicial){
        StringBuilder detalhesCarrinho = new StringBuilder("***Carrinho:\n");
        for (ItemPedido item : itens){
            detalhesCarrinho.append("\n--Restaurante: ").append(item.getNomeRestaurante());
            detalhesCarrinho.append("\n--Produto: ").append(item.getPedido().getNome())
                    .append("\n-Quantidade: ").append(item.getQuantidade())
                    .append("\n-Preço total produtos: R$").append(item.getPrecoTotalProd())
                    .append("\n\n--Acompanhamentos: ");
            for(Acompanhamento acompanhamento : item.getAcompanhamentos()){
                detalhesCarrinho.append("\n-Nome: ").append(acompanhamento.getNome())
                        .append("\n-Quantidade: ").append(acompanhamento.getQuantidade())
                        .append("\n-Preço total acompanhamentos: R$").append(acompanhamento.getValor_total())
                        .append("\n");
            }

        }

        detalhesCarrinho.append("\n\n--Preço total do pedido: R$").append(getValorTotal()).append("\n");
        //JOptionPane.showMessageDialog(null, detalhesCarrinho.toString(),
        //        "Carrinho", JOptionPane.INFORMATION_MESSAGE);

        // Cria painel com botões
        JPanel panel = new JPanel(); panel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(detalhesCarrinho.toString());
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Botões
        JButton adicionarProdutos = new JButton("Adicionar mais produtos");
        JButton adicionarEndereco = new JButton("Adicionar endereço de entrega");

        // Ações dos botões
        adicionarProdutos.addActionListener(e -> {
            JOptionPane.getRootFrame().dispose();// Fecha o diálogo atual
            menu_inicial.listarRestaurantes();// Retorna à listagem de restaurantes
        });
        adicionarEndereco.addActionListener(e -> {
            JOptionPane.getRootFrame().dispose(); // Fecha o diálogo atual
            adicionarEndereco();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarProdutos);
        buttonPanel.add(adicionarEndereco);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        JOptionPane.showMessageDialog(null, panel, "Carrinho de Compras", JOptionPane.INFORMATION_MESSAGE);
    }

    private void adicionarEndereco(){
        JTextField ruaField = new JTextField();
        JTextField bairroField = new JTextField();
        JTextField cidadeField = new JTextField();
        JTextField estadoField = new JTextField();
        JTextField numeroCasaField = new JTextField();
        JTextField complementoField = new JTextField();
        JTextField pontoReferenciaField = new JTextField();
        JTextField cepField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("Rua:"));
        panel.add(ruaField);

        panel.add(new JLabel("Bairro:"));
        panel.add(bairroField);

        panel.add(new JLabel("Cidade:"));
        panel.add(cidadeField);

        panel.add(new JLabel("Estado:"));
        panel.add(estadoField);

        panel.add(new JLabel("Número:"));
        panel.add(numeroCasaField);

        panel.add(new JLabel("Complemento:"));
        panel.add(complementoField);

        panel.add(new JLabel("Ponto de Referência:"));
        panel.add(pontoReferenciaField);

        panel.add(new JLabel("CEP:"));
        panel.add(cepField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Endereço de Entrega", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Endereco endereco = new Endereco( 0, // ID fictício para exemplo
                    ruaField.getText(),
                    bairroField.getText(),
                    cidadeField.getText(),
                    Integer.parseInt(numeroCasaField.getText()),
                    pontoReferenciaField.getText(), cepField.getText()
            );

            endereco.setEstado(estadoField.getText());
            endereco.setComplemento(complementoField.getText());

            JOptionPane.showMessageDialog(null, "Endereço adicionado: \n"
                    + endereco.getRua() + ", " + endereco.getNumeroCasa() + "\n" + endereco.getBairro() +
                    " - " + endereco.getCidade() + ", " + endereco.getEstado() + "\n" + "CEP: "
                    + endereco.getCep() + "\n" + "Ponto de Referência: " + endereco.getPontoReferencia()
                    + "\n" + "Complemento: " + endereco.getComplemento());

        }
    }
}
