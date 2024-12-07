package Entity;

import javax.management.timer.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;


public class Carrinho {
    private Endereco endereco;
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


        JPanel panel = new JPanel(); panel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(detalhesCarrinho.toString());
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);


        JButton adicionarProdutos = new JButton("Adicionar mais produtos");
        JButton adicionarEndereco = new JButton("Adicionar endereço de entrega");


        adicionarProdutos.addActionListener(e -> {
            JOptionPane.getRootFrame().dispose();
            menu_inicial.listarRestaurantes();
        });
        adicionarEndereco.addActionListener(e -> {
            JOptionPane.getRootFrame().dispose();
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

        panel.add(new JLabel("*CEP:"));
        panel.add(cepField);

        panel.add(new JLabel("*Cidade:"));
        panel.add(cidadeField);

        panel.add(new JLabel("Estado:"));
        panel.add(estadoField);

        panel.add(new JLabel("*Rua:"));
        panel.add(ruaField);

        panel.add(new JLabel("*Bairro:"));
        panel.add(bairroField);

        panel.add(new JLabel("*Número:"));
        panel.add(numeroCasaField);

        panel.add(new JLabel("*Complemento:"));
        panel.add(complementoField);

        panel.add(new JLabel("*Ponto de Referência:"));
        panel.add(pontoReferenciaField);


        cepField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e){
                buscarCep(cepField.getText(), cidadeField, estadoField);
            }
        });

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Endereço de Entrega", JOptionPane.OK_CANCEL_OPTION);

        boolean enderecoValido = false;

        while (!enderecoValido){
            if (result == JOptionPane.OK_OPTION) {
                if (validarDadosEndereco(ruaField, bairroField, cidadeField, numeroCasaField, pontoReferenciaField, cepField)){
                    endereco = new Endereco( 0, // ID fictício para exemplo
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
                    enderecoValido = true;

                    formaPagamento();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, preencha todos os campos obrigatórios.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    adicionarEndereco();
                }
            } else {
                enderecoValido = true;
            }
        }
    }
    private boolean validarDadosEndereco(JTextField ruaField, JTextField bairroField, JTextField cidadeField, JTextField numeroCasaField, JTextField pontoReferenciaField, JTextField cepField){

        return !ruaField.getText().isEmpty() &&
                !bairroField.getText().isEmpty() &&
                !cidadeField.getText().isEmpty() &&
                !numeroCasaField.getText().isEmpty() &&
                !pontoReferenciaField.getText().isEmpty() &&
                !cepField.getText().isEmpty();
    }

    private void buscarCep(String cep, JTextField cidadeField, JTextField estadoField){
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputline;
            while ((inputline = br.readLine()) != null){
                response.append(inputline);
            }
            br.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            String cidade = jsonObject.getString("localidade");
            String estado = jsonObject.getString("uf");

            cidadeField.setText(cidade);
            estadoField.setText(estado);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar o CEP." +
                    " Verifique e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formaPagamento(){

        String[] opcoesPagamento = {"Pix", "Dinheiro", "Cartão"};
        String escolhaPagamento = (String) JOptionPane.showInputDialog(null,
                "Escolha a forma de pagamento:", "Forma de pagamento",
                JOptionPane.QUESTION_MESSAGE, null, opcoesPagamento, opcoesPagamento[0]);

        if (escolhaPagamento != null){
            switch (escolhaPagamento){
                case "Pix":
                    telaPix(getValorTotal());
                    break;

                case "Dinheiro":
                    String[] opcoesMenuAcomp = { "Sim", "Não" };
                    int respostaTroco = JOptionPane.showOptionDialog(null, "Você precisa" +
                            " de Troco? \n Total: R$" + getValorTotal(), "Troco", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null ,opcoesMenuAcomp, opcoesMenuAcomp[0]);

                    if (respostaTroco == 0) {
                        boolean valorTrocoValido = false;
                        while (!valorTrocoValido) {
                            String valorTrocoStr = JOptionPane.showInputDialog("Para quanto você precisa de Troco?");

                            if (valorTrocoStr != null && !valorTrocoStr.isEmpty()) {
                                try{
                                    double valorTroco = Double.parseDouble(valorTrocoStr);
                                    if (valorTroco > getValorTotal()){
                                        valorTrocoValido = true;
                                        JOptionPane.showMessageDialog(null,
                                                "Você escolheu pagar com dinheiro e precisa de troco para: R$" + valorTrocoStr);
                                        resumoPedido(endereco, "Dinheiro", true, valorTroco);
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                "O valor do troco deve ser maior ou igual ao valor total do pedido: R$ "
                                                        + getValorTotal(), "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NumberFormatException e){
                                    JOptionPane.showMessageDialog(null,
                                            "Por favor, insira um valor válido para o troco.",
                                            "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Você escolheu pagar com dinheiro e não precisa de troco.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Você escolheu pagar com dinheiro e não precisa de troco.");
                    }
                    break;

                case "Cartão":
                    escolhaCartao();
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Forma de pagamento inválida. Por favor, escolha novamente.");
                    formaPagamento();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Nenhuma forma de pagamento selecionada. Por favor, escolha uma forma de pagamento.");
            formaPagamento();
        }
    }

    private void telaPix(double valorTotal){
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JLabel valorLabel = new JLabel("Valor do Pix: R$ " + valorTotal);
        JLabel chavePixLabel = new JLabel("Chave Pix: chavepix@exemplo.com");
        JButton pixFinalizadoButton = new JButton("Pix finalizado");

        pixFinalizadoButton.addActionListener(e -> {
            JOptionPane.getRootFrame().dispose(); // Fecha o diálogo atual
            // Chame a próxima etapa ou tela aqui
            JOptionPane.showMessageDialog(null, "Pix finalizado com sucesso!");
            resumoPedido(endereco, "Pix", false, 0);
        });

     panel.add(valorLabel);
     panel.add(chavePixLabel);
     panel.add(pixFinalizadoButton);

     JOptionPane.showMessageDialog(null, panel, "Pagamento via Pix", JOptionPane.INFORMATION_MESSAGE);
    }

    private void escolhaCartao(){
        String[] opcoesCartao = {"Crédito", "Débito"};
        String escolhaCartao = (String) JOptionPane.showInputDialog( null,
                "Escolha o tipo de cartão:", "Tipo de Cartão",
                JOptionPane.QUESTION_MESSAGE, null, opcoesCartao, opcoesCartao[0]);

        if (escolhaCartao != null) {
            JOptionPane.showMessageDialog(null,
                    "Você escolheu pagar com Cartão de " + escolhaCartao +
                            ". O pagamento será feito na entrega.");
            resumoPedido(endereco, "Cartão de " + escolhaCartao, false, 0);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Nenhuma opção de cartão selecionada. Por favor, escolha uma opção.");
            escolhaCartao();
        }
    }

    private void resumoPedido(Endereco endereco, String formaPagamento, boolean precisaTroco, double valorTroco) {
        JFrame frame = new JFrame("Resumo do Pedido");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        StringBuilder resumo = new StringBuilder();

        resumo.append("***Endereço de Entrega:\n");
        resumo.append("Rua: ").append(endereco.getRua()).append(", Nº").append(endereco.getNumeroCasa())
                .append(" - ").append(endereco.getBairro());
        resumo.append("\nCep: ").append(endereco.getCep()).append(" - ").append(endereco.getCidade())
                .append(" - ").append(endereco.getEstado());
        resumo.append("\nPonto de referência: ").append(endereco.getPontoReferencia());
        if (!endereco.getComplemento().isEmpty()) {
            resumo.append("\nComplemento: ").append(endereco.getComplemento()).append("\n\n");
        }

        double precoTotalProd = 0.0;
        for (ItemPedido item : itens) {
            resumo.append("***Restaurante escolhido: ").append(item.getNomeRestaurante());
            resumo.append("\n--Produtos escolhidos:");
            resumo.append("\n-Nome: ").append(item.getPedido().getNome());
            resumo.append("\n-Quantidade: ").append(item.getQuantidade());
            resumo.append("\n-Preço total produtos: R$").append(item.getPrecoTotalProd());
            resumo.append("\n\n--Acompanhamentos:");
            for (Acompanhamento acompanhamento : item.getAcompanhamentos()) {
                resumo.append("\n-Nome: ").append(acompanhamento.getNome())
                        .append("\n-Quantidade: ").append(acompanhamento.getQuantidade())
                        .append("\n-Preço total acomapanhamentos: R$").append(acompanhamento.getValor_total());
            }
            precoTotalProd += item.getPrecoTotalProd();
        }
        resumo.append("\n\n--Preço total do pedido: R$").append(getValorTotal());

        double precoTotalAcomp = 0.0;
        for (ItemPedido item : itens) {
            for (Acompanhamento acompanhamento : item.getAcompanhamentos()) {
                precoTotalAcomp = precoTotalAcomp + acompanhamento.getValor_total();
            }
        }
        double precoTotalPedido = precoTotalAcomp + precoTotalProd;
        resumo.append("\n\n***Forma de Pagamento: ").append(formaPagamento);
        if (formaPagamento.equals("Dinheiro")) {
            if (precisaTroco) {
                resumo.append("\n-Precisa de Troco: Sim");
                resumo.append("\n-Troco para: R$").append(valorTroco);
                double calcTroco = valorTroco - precoTotalPedido;
                resumo.append("\n-Troco: R$").append(calcTroco);
            } else {
                resumo.append("\n-Precisa de Troco: Não\n");
            }
        }



        JTextArea textArea = new JTextArea(resumo.toString());
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton concluirPedidoButton = new JButton("Concluir pedido");
        concluirPedidoButton.addActionListener(e -> {
            frame.dispose();
            Status_Pedido statusPedido = new Status_Pedido();
            javax.swing.Timer timer = new javax.swing.Timer(2000, event ->{
                int progresso = statusPedido.progressBar.getValue();
                if (progresso == 0){
                    statusPedido.atualizarStatus("Pedido em produção", 33);
                } else if (progresso == 33) {
                    statusPedido.atualizarStatus("Pedido a caminho", 66);
                } else if (progresso == 66) {
                    statusPedido.atualizarStatus("Pedido entregue", 100);
                    ((Timer) event.getSource()).stop();
                }
            });
            timer.start();
        });
        frame.add(concluirPedidoButton, BorderLayout.SOUTH);
        frame.setVisible(true);


    }
}
