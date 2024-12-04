package Entity;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {
    private Pedido pedido;
    //private Restaurante restaurante;
    //private Produto produto;
    private int quantidade;
    private List<Acompanhamento> acompanhamentos;
    private double precoTotalProd;
    private double precoTotalAcomp;
    private double precoTotal;

    ItemPedido(Pedido pedido, int quantidade) {
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.acompanhamentos = new ArrayList<>();
    }

    public Pedido getPedido(){
        return pedido;
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
        precoTotalProd = pedido.getPreco() * quantidade;
        for (Acompanhamento acompanhamento : acompanhamentos) {
            precoTotalAcomp = acompanhamento.getValor_total();
        }
        precoTotal = precoTotalProd + precoTotalAcomp;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public double getPrecoTotalProd() {
        return precoTotalProd;
    }

    public double getPrecoTotalAcomp(){
        return precoTotalAcomp;
    }
}
