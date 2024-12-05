package Entity;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {
    private Pedido pedido;
    private int quantidade;
    private List<Acompanhamento> acompanhamentos;
    private double precoTotalProd;
    private double precoTotalAcomp;
    private double precoTotal;

    ItemPedido(Pedido pedido, int quantidade) {
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.acompanhamentos = new ArrayList<>();
        calcularPrecoTotal();
    }

    public Pedido getPedido(){
        return pedido;
    }

    public String getNomeRestaurante(){
        return getPedido().getNomeRestaurante();
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
        precoTotalProd = pedido.getPrecoProd() * quantidade;
        for (Acompanhamento acompanhamento : acompanhamentos) {
            precoTotalAcomp += acompanhamento.getValor_total();
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
