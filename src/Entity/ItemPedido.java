package Entity;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {
    private Restaurante restaurante;
    private Produto produto;
    private int quantidade;
    private List<Acompanhamento> acompanhamentos;
    private double precoTotalProd;
    private double precoTotalAcomp;
    private double precoTotal;

    ItemPedido(Restaurante restaurante, Produto produto, int quantidade) {
        this.restaurante = restaurante;
        this.produto = produto;
        this.quantidade = quantidade;
        this.acompanhamentos = new ArrayList<>();
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public List<Acompanhamento> getAcompanhamentos() {
        return acompanhamentos;
    }


    public void adicionarAcompanhamento(Acompanhamento acompanhamento) {
        double preco = acompanhamento.getValor() * acompanhamento.getQuantidade();
        acompanhamento.setValor_total(preco);
        acompanhamentos.add(acompanhamento);
        calcularPrecoTotal();
    }

    private void calcularPrecoTotal() {
        precoTotalProd = produto.getPreco() * quantidade;
        for (Acompanhamento acompanhamento : acompanhamentos) {
            precoTotalAcomp = acompanhamento.getValor_total();
        }
        precoTotal = precoTotalProd + precoTotalAcomp;
    }

    public double getPrecoTotal() {
        //calcularPrecoTotal();
        return precoTotal;
    }

    public double getPrecoTotalProd() {
        return precoTotalProd;
    }
}
