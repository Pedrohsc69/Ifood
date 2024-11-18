import javax.swing.JOptionPane;
import java.util.List;
import java.util.Scanner;


public class Menu_Inicial {

    Scanner scanner = new Scanner(System.in);
    Opcs_Menu opcoes = new Opcs_Menu();

    private Carrinho carrinho;
    private List<Associacao_Rest_Prod> Assoc_Rest_Prod;
    private List<Associacao_Prod_Acomp> Assoc_Prod_Acomp;



    public Menu_Inicial(List<Associacao_Rest_Prod> associacoes, List<Associacao_Prod_Acomp> Assoc_Prod_Acomp) {
        this.Assoc_Rest_Prod = associacoes;
        this.Assoc_Prod_Acomp = Assoc_Prod_Acomp;
        this.carrinho = new Carrinho();
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }




    public void Exibir_Menu() {
        String[] opcoesMenuPrincipal = { "Novo pedido", "Sair" };
        int escolha = JOptionPane.showOptionDialog(null, "******SEJA BEM VINDO******", "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPrincipal, opcoesMenuPrincipal[0]);

        if (escolha == 0) {  // Novo pedido
            listarRestaurantes();
        } else {
            JOptionPane.showMessageDialog(null, "Saindo...");
        }
    }

    private void listarRestaurantes() {
        String[] opcoesRestaurantes = new String[Assoc_Rest_Prod.size() + 1];
        for (int i = 0; i < Assoc_Rest_Prod.size(); i++) {
            Associacao_Rest_Prod associacao = Assoc_Rest_Prod.get(i);
            Restaurante restaurante = associacao.getRestaurante();
            opcoesRestaurantes[i] = restaurante.getNome();
        }
        opcoesRestaurantes[Assoc_Rest_Prod.size()] = "Voltar para menu inicial";

         int escolhaRestaurante = JOptionPane.showOptionDialog(null, "Escolha um restaurante", "Restaurantes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesRestaurantes, opcoesRestaurantes[0]);

        if (escolhaRestaurante < Assoc_Rest_Prod.size()) {
            Associacao_Rest_Prod associacaoRestaurante = Assoc_Rest_Prod.get(escolhaRestaurante);
            Listar_Produtos(associacaoRestaurante);
        } else {
            Exibir_Menu();
        }
    }

    private void Listar_Produtos(Associacao_Rest_Prod associacaoRestaurante) {
        List<Produto> produtos = associacaoRestaurante.getProdutos();
        String[] opcoesProdutos = new String[produtos.size() + 1];
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            opcoesProdutos[i] = produto.getNome();
        }
        opcoesProdutos[produtos.size()] = "Voltar para menu de restaurantes";

        int escolhaProduto = JOptionPane.showOptionDialog(null, "Escolha um produto", "Produtos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesProdutos, opcoesProdutos[0]);

        if (escolhaProduto < produtos.size()) {
            Produto produtoEscolhido = produtos.get(escolhaProduto);
            String quantidadeStr = JOptionPane.showInputDialog("Quantos " + produtoEscolhido.getNome() + " você deseja?");
            int quantidade = Integer.parseInt(quantidadeStr);
            ItemPedido itemPedido = new ItemPedido(associacaoRestaurante.getRestaurante(), produtoEscolhido, quantidade);
            listarAcompanhamentos(itemPedido, associacaoRestaurante);
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
                int escolhaAcompanhamento = JOptionPane.showOptionDialog(null, "Escolha um Acompanhemento", "Acompanhamento",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesAcompanhamentos, opcoesAcompanhamentos[0]);

                if (escolhaAcompanhamento < acompanhamentos.size()) {
                    Acompanhamento acompanhamentoEscolhido = acompanhamentos.get(escolhaAcompanhamento);
                    String quantidadeStr = JOptionPane.showInputDialog("Quantos(as) " + acompanhamentoEscolhido.getNome() + " você deseja?");
                    int quantidade = Integer.parseInt(quantidadeStr);
                    Acompanhamento novoAcomp = new Acompanhamento(acompanhamentoEscolhido.getNome(), acompanhamentoEscolhido.getValor(), acompanhamentoEscolhido.getDescricao());
                    novoAcomp.setQuantidade(quantidade);
                    itemPedido.adicionarAcompanhamento(novoAcomp);
                    JOptionPane.showMessageDialog(null, quantidade + " " + acompanhamentoEscolhido.getNome() + " adicionado(s) ao seu pedido.");
                }

                carrinho.adicionarItem(itemPedido);

                String[] opcoesMaisProduto = { "Sim", "Não" };
                int adicionarMaisProduto = JOptionPane.showOptionDialog(null, "Deseja adicionar mais algum produto ao seu pedido?", "Adicionar Produto",
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


