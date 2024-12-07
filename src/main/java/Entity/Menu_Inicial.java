package Entity;

import Conexao.Conexao_BD;


import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;


public class Menu_Inicial extends JDialog{

    private Connection conexao;
    private Carrinho carrinho;
    private List<Associacao_Rest_Prod> Assoc_Rest_Prod;
    private List<Associacao_Prod_Acomp> Assoc_Prod_Acomp;


    public Menu_Inicial(List<Associacao_Rest_Prod> associacoes, List<Associacao_Prod_Acomp> Assoc_Prod_Acomp) {
        this.Assoc_Rest_Prod = associacoes;
        this.Assoc_Prod_Acomp = Assoc_Prod_Acomp;
        this.carrinho = new Carrinho(this);
        this.conexao = Conexao_BD.getConexao();
        carregarAssociacoesRest();
    }

    public Menu_Inicial() {
        this.carrinho = new Carrinho(this);
        this.conexao = Conexao_BD.getConexao();
        carregarAssociacoesRest();
        carregarAssociacaoProd();
    }

    private void carregarAssociacoesRest(){
        try{

            String query = "SELECT r.id, r.nome, p.id AS prod_id, p.nome AS prod_nome, p.preco" +
                    "FROM restaurante AS r " +
                    "JOIN produto AS p ON r.id = p.id_restaurante";

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

    public void carregarAssociacaoProd(){
        try{
            String query = "SELECT p.id, a.id AS acomp_id, a.nome AS acomp_nome, a.descricao, a.preco" +
                    "FROM produto p" +
                    "JOIN acompanhamento a ON p.id = a.id_produto";

            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Associacao_Prod_Acomp associacaoAtual = null;
            int produtoAtualId = -1;
            while (resultSet.next()){
                int produtoId = resultSet.getInt("id");
                if (produtoId != produtoAtualId){
                    Produto produto = new Produto(resultSet.getInt("id"),
                            resultSet.getString("nome"), resultSet.getDouble("preco"));
                    associacaoAtual = new Associacao_Prod_Acomp(produto);
                    Assoc_Prod_Acomp.add(associacaoAtual);
                    produtoAtualId =  produtoId;
                }

                Acompanhamento acompanhamento = new Acompanhamento(resultSet.getInt("acomp_id"),
                        resultSet.getString("nome"), resultSet.getDouble("preco"),
                        resultSet.getString("descricao"));
                associacaoAtual.Add_Acomp(acompanhamento);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void Exibir_Menu() {
        carrinho.limparCarrinho();

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

    void listarRestaurantes() {
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
            //listarAcompanhamentos(produtoEscolhido, associacaoRestaurante.getRestaurante(), quantidade);
            perguntaAcompanhamento(produtoEscolhido, associacaoRestaurante.getRestaurante(), quantidade);
        } else {
            listarRestaurantes();
        }

    }

    private void perguntaAcompanhamento(Produto produtoEscolhido, Restaurante restauranteEscolhido, int quantidade){
        String[] opcoesMenuAcomp = { "Sim", "Não" };

        int resposta = JOptionPane.showOptionDialog(null,
                "Você deseja adicionar acompanhamentos?", "Acompanhamentos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null, opcoesMenuAcomp, opcoesMenuAcomp[0]);

        Pedido pedido = new Pedido(produtoEscolhido.getNome(), produtoEscolhido.getDescricao(),
                produtoEscolhido.getPreco(), null, restauranteEscolhido);
        ItemPedido itemPedido = new ItemPedido(pedido, quantidade);

        if (resposta == 0){
            listarAcompanhamentos(produtoEscolhido, restauranteEscolhido, quantidade);
        } else {
            carrinho.adicionarItem(itemPedido);
            carrinho.ExibirCarrinho(this);
        }

    }
    private void listarAcompanhamentos(Produto produtoEscolhido, Restaurante restauranteEscolhido, int quantidade) {

        for (Associacao_Prod_Acomp assocProdAcomp : Assoc_Prod_Acomp){
            if (assocProdAcomp.getProduto().equals(produtoEscolhido)){
                List<Acompanhamento> acompanhamentos = assocProdAcomp.getAcompanha();
                String[] opcoesAcompanha = new String[acompanhamentos.size() + 1];

                for (int i = 0; i < acompanhamentos.size(); i++){
                    Acompanhamento acompanhamento = acompanhamentos.get(i);
                    opcoesAcompanha[i] = acompanhamento.getNome() + " - R$" + acompanhamento.getValor();
                }
                opcoesAcompanha[acompanhamentos.size()] = "Concluir Pedido";

                List<Acompanhamento> acompanhamentosEscolhidos = new ArrayList<>();
                
                int escolhaAcompanha;
                do {
                    escolhaAcompanha = JOptionPane.showOptionDialog(null,
                            "Acompanhamentos disponíveis para " + produtoEscolhido.getNome(),
                            "Acompanhamentos", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, opcoesAcompanha, opcoesAcompanha[0]);

                    if (escolhaAcompanha < acompanhamentos.size()){
                        Acompanhamento acompanhaEscolhido = acompanhamentos.get(escolhaAcompanha);
                        String quantidadeStr = JOptionPane.showInputDialog("Quantos " +
                                acompanhaEscolhido.getNome() + " você deseja?");
                        int quantidadeAcomp = Integer.parseInt(quantidadeStr);
                        
                        acompanhaEscolhido.setQuantidade(quantidadeAcomp);

                        acompanhamentosEscolhidos.add(acompanhaEscolhido);
                    }

                } while (escolhaAcompanha < acompanhamentos.size());

                Pedido pedido = new Pedido(produtoEscolhido.getNome(), produtoEscolhido.getDescricao(),
                        produtoEscolhido.getPreco(), null, restauranteEscolhido);
                ItemPedido itemPedido = new ItemPedido(pedido, quantidade);

                for (Acompanhamento acompanhamento : acompanhamentosEscolhidos) {
                    itemPedido.adicionarAcompanhamento(acompanhamento);
                }
                carrinho.adicionarItem(itemPedido);

                carrinho.ExibirCarrinho(this);

            }
        }
    }
}


