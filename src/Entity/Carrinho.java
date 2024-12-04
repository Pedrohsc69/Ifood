package Entity;

import javax.swing.*;
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
        valorTotal += item.getPrecoTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void ExibirCarrinho(){
        StringBuilder detalhesCarrinho = new StringBuilder("***Carrinho:\n");
        for (ItemPedido item : itens){
            detalhesCarrinho.append("--Produto: ").append(item.getPedido().getNome())
                    .append("\n-Quantidade: ").append(item.getQuantidade())
                    .append("\n-Preço total produtos: R$").append(item.getPrecoTotalProd())
                    .append("\n--Acompanhamentos: ");
            for(Acompanhamento acompanhamento : item.getAcompanhamentos()){
                detalhesCarrinho.append("\n-Nome: ").append(acompanhamento.getNome())
                        .append("\n-Preço total acompanhamentos: R$").append(acompanhamento.getValor_total())
                        .append("\n");
            }
            detalhesCarrinho.append("\n--Preço total acompanhamentos: R$").append(item.getPrecoTotalAcomp()).append("\n\n");
        }
        detalhesCarrinho.append("--Preço total do pedido: R$").append(getValorTotal()).append("\n");
        JOptionPane.showMessageDialog(null, detalhesCarrinho.toString(),
                "Carrinho", JOptionPane.INFORMATION_MESSAGE);
    }

}
