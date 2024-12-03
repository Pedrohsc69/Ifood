package Entity;

import Conexao.Conexao_BD;


import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;


public class Menu_Inicial extends JDialog{

    //Scanner scanner = new Scanner(System.in);
    //Opcs_Menu opcoes = new Opcs_Menu();

    private Connection conexao;
    private Carrinho carrinho;
    private List<Associacao_Rest_Prod> Assoc_Rest_Prod;
    private List<Associacao_Prod_Acomp> Assoc_Prod_Acomp;



    public Menu_Inicial(List<Associacao_Rest_Prod> associacoes, List<Associacao_Prod_Acomp> Assoc_Prod_Acomp) {
        this.Assoc_Rest_Prod = associacoes;
        this.Assoc_Prod_Acomp = Assoc_Prod_Acomp;
        this.carrinho = new Carrinho();
        this.conexao = Conexao_BD.getConexao();
        carregarAssociacoesRest();
    }

    //public static void clearConsole() {
    //    for (int i = 0; i < 50; i++) {
    //        System.out.println();
    //    }
    //}

    private void carregarAssociacoesRest(){
        try{

            String query = "SELECT r.id, r.nome, p.id AS prod_id, p.nome AS prod_nome, p.preco" +
                    "FROM restaurante r " +
                    "JOIN produto p ON r.id = p.id_restaurante";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Associacao_Rest_Prod associacaoAtual = null;
            int restaurenteAtualId = -1;
            while (resultSet.next()){
                int restauranteID = resultSet.getInt("id");
                if (restauranteID != restaurenteAtualId){
                    Restaurante restaurante = new Restaurante(resultSet.getInt("id"),
                            resultSet.getString("nome"));
                    associacaoAtual = new Associacao_Rest_Prod(restaurante);
                    Assoc_Rest_Prod.add(associacaoAtual);
                    restaurenteAtualId = restauranteID;
                }
                Produto produto = new Produto(resultSet.getInt("id"),
                        resultSet.getString("nome"), resultSet.getDouble("preco"));
                associacaoAtual.Add_Produto(produto);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void Exibir_Menu() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("<html><body style='width: 250px;'>" +
                "<h2 style='color: red; font-size: 20px;'>***SEJA BEM VINDO***</h2> </body></html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);

        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel labelExit = new JLabel("<html><body style='width: 250px;'>" +
                "<h2 style='color: Black; font-size: 20px; margin-left: 5px;'>Saindo...</h2> " +
                "</body></html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(labelExit);

        String[] opcoesMenuPrincipal = { "Novo pedido", "Sair" };

        String caminhoIcon = "C:/Users/Flávio Cavalcanti/Desktop/PEDRO/FACULDADE/2º PERIODO/JAVA POO/Ifood/src/Entity/Icons/ifood.png";
        ImageIcon icon = new ImageIcon(caminhoIcon);

        String caminhoIconExit = "C:/Users/Flávio Cavalcanti/Desktop/PEDRO/FACULDADE/2º PERIODO/JAVA POO/Ifood/src/Entity/Icons/saindo.png";
        ImageIcon iconExit = new ImageIcon(caminhoIconExit);

        int escolha = JOptionPane.showOptionDialog(null, panel, "Ifood",
               JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, opcoesMenuPrincipal, opcoesMenuPrincipal[0]);

        if (escolha == 0) {  // Novo pedido
            listarRestaurantes();
        } else {
            JOptionPane.showMessageDialog(null, panel1, "Ifood", JOptionPane.PLAIN_MESSAGE, iconExit);
        }
    }

    private void listarRestaurantes() {
        //String[] opcoesRestaurantes = new String[Assoc_Rest_Prod.size() + 1];
        //for (int i = 0; i < Assoc_Rest_Prod.size(); i++) {
        //    Associacao_Rest_Prod associacao = Assoc_Rest_Prod.get(i);
        //    Restaurante restaurante = associacao.getRestaurante();
        //    opcoesRestaurantes[i] = restaurante.getNome();
        //}
        //opcoesRestaurantes[Assoc_Rest_Prod.size()] = "Voltar para menu inicial";

        // int escolhaRestaurante = JOptionPane.showOptionDialog(null, "Escolha um restaurante", "Restaurantes",
        //        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesRestaurantes, opcoesRestaurantes[0]);

        //if (escolhaRestaurante < Assoc_Rest_Prod.size()) {
        //    Associacao_Rest_Prod associacaoRestaurante = Assoc_Rest_Prod.get(escolhaRestaurante);
        //    Listar_Produtos(associacaoRestaurante);
        //} else {
        //    Exibir_Menu();
        //}

        String[] opcaoRestaurante = new String[Assoc_Rest_Prod.size() + 1];
        for (int i = 0; i < Assoc_Rest_Prod.size(); i++){
            Associacao_Rest_Prod associacao = Assoc_Rest_Prod.get(i);
            Restaurante restaurante = associacao.getRestaurante();
            opcaoRestaurante[i] = restaurante.getNome();
        }
        opcaoRestaurante[Assoc_Rest_Prod.size()] = "Voltar para menu inicial";

        String caminhoIcon = "C:/Users/Flávio Cavalcanti/Desktop/PEDRO/FACULDADE/2º PERIODO/JAVA POO/Ifood/src/Entity/Icons/restaurante.png";
        ImageIcon iconRest = new ImageIcon(caminhoIcon);

        String message = "<html><body style='width: 250px;'>" +
                "<h2>Restaurantes disponíveis</h2>" +
                "<p>Por favor, selecione um restaurante da lista abaixo:</p>" +
                "</body></html>";

        int escolhaRestaurante = JOptionPane.showOptionDialog(null, message, "Restaurantes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, iconRest, opcaoRestaurante, opcaoRestaurante[0]);

        if (escolhaRestaurante < Assoc_Rest_Prod.size()){
            Associacao_Rest_Prod associacaoRestProd = Assoc_Rest_Prod.get(escolhaRestaurante);
            Listar_Produtos(associacaoRestProd);
        } else {
            Exibir_Menu();
        }

    }

    private void Listar_Produtos(Associacao_Rest_Prod associacaoRestaurante) {
        //List<Produto> produtos = associacaoRestaurante.getProdutos();
        //String[] opcoesProdutos = new String[produtos.size() + 1];
        //for (int i = 0; i < produtos.size(); i++) {
        //    Produto produto = produtos.get(i);
        //    opcoesProdutos[i] = produto.getNome();
        //}
        //opcoesProdutos[produtos.size()] = "Voltar para menu de restaurantes";

        //int escolhaProduto = JOptionPane.showOptionDialog(null, "Escolha um produto", "Produtos",
        //        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesProdutos, opcoesProdutos[0]);

        //if (escolhaProduto < produtos.size()) {
        //    Produto produtoEscolhido = produtos.get(escolhaProduto);
        //    String quantidadeStr = JOptionPane.showInputDialog("Quantos " + produtoEscolhido.getNome() + " você deseja?");
        //    int quantidade = Integer.parseInt(quantidadeStr);
        //    ItemPedido itemPedido = new ItemPedido(associacaoRestaurante.getRestaurante(), produtoEscolhido, quantidade);
        //    listarAcompanhamentos(itemPedido, associacaoRestaurante);
        //} else {
        //    listarRestaurantes();
        //}

        List<Produto> produtos = associacaoRestaurante.getProdutos();
        String[] opcoesProdutos = new String[produtos.size() + 1];
        for (int i = 0; i < produtos.size(); i++){
            Produto produto = produtos.get(i);
            opcoesProdutos[i] = produto.getNome() + "- R$" + produto.getPreco();
        }
        opcoesProdutos[produtos.size()] = "Voltar para o menu de restaurantes";

        int escolhaProduto = JOptionPane.showOptionDialog(null,
                "Produtos disponíveis em " + associacaoRestaurante.getNome_Restaurante(), "Produtos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesProdutos, opcoesProdutos[0]);

        if (escolhaProduto < produtos.size()){
            Produto produtoEscolhido = produtos.get(escolhaProduto);
            String quantidadeStr = JOptionPane.showInputDialog("Quantos " + produtoEscolhido.getNome()
                    + " você deseja?");
            int quantidade = Integer.parseInt(quantidadeStr);
        } else {
            listarRestaurantes();
        }

    }

    private void listarAcompanhamentos(ItemPedido itemPedido, Associacao_Rest_Prod associacaoRestaurante) {
        for (Associacao_Prod_Acomp associacaoProduto : Assoc_Prod_Acomp) {
            if (associacaoProduto.getProduto().equals(itemPedido.getProduto())) {
                List<Acompanhamento> acompanhamentos = associacaoProduto.getAcompanha();
                String[] opcoesAcompanhamentos = new String[acompanhamentos.size()];
                for (int i = 0; i < acompanhamentos.size(); i++) {
                    Acompanhamento acompanhamento = acompanhamentos.get(i);
                    opcoesAcompanhamentos[i] = acompanhamento.getNome() + " - R$" + acompanhamento.getValor();
                }
                int escolhaAcompanhamento = JOptionPane.showOptionDialog(null, "Escolha um Acompanhemento", "Entity.Acompanhamento",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesAcompanhamentos, opcoesAcompanhamentos[0]);

                if (escolhaAcompanhamento < acompanhamentos.size()) {
                    Acompanhamento acompanhamentoEscolhido = acompanhamentos.get(escolhaAcompanhamento);
                    String quantidadeStr = JOptionPane.showInputDialog("Quantos(as) " + acompanhamentoEscolhido.getNome() + " você deseja?");
                    int quantidade = Integer.parseInt(quantidadeStr);
                    acompanhamentoEscolhido.setQuantidade(quantidade);
                    itemPedido.adicionarAcompanhamento(acompanhamentoEscolhido);
                    JOptionPane.showMessageDialog(null, quantidade + " " + acompanhamentoEscolhido.getNome() + " adicionado(s) ao seu pedido.");
                }

                carrinho.adicionarItem(itemPedido);

                String[] opcoesMaisProduto = { "Sim", "Não" };
                int adicionarMaisProduto = JOptionPane.showOptionDialog(null, "Deseja adicionar mais algum produto ao seu pedido?", "Adicionar Entity.Produto",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMaisProduto, opcoesMaisProduto[0]);

                if (adicionarMaisProduto == 0) { // Sim
                    Listar_Produtos(associacaoRestaurante);
                } else {
                    carrinho.exibirCarrinho();
                }
                return;
            }
        }
    }


}


