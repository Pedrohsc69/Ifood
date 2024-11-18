import java.util.ArrayList;
import java.util.List;

public class ItemPedido {
    private Restaurante restaurante;
    private Produto produto;
    private int quantidade;
    private List<Acompanhamento> acompanhamentos;
    private double precoTotal;

    ItemPedido(Restaurante restaurante, Produto produto, int quantidade) {
        this.restaurante = restaurante;
        this.produto = produto;
        this.quantidade = quantidade;
        this.acompanhamentos = new ArrayList<>();
        this.precoTotal = produto.getPreco() * quantidade;
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

    public double getPrecoTotal() {
        return precoTotal;
    }
    private void calcularPrecoTotal() {
        precoTotal = produto.getPreco() * quantidade;
        for (Acompanhamento acompanhamento : acompanhamentos) {
            precoTotal += acompanhamento.getValor_total();
        }
    }

    public void adicionarAcompanhamento(Acompanhamento acompanhamento) {
        acompanhamentos.add(acompanhamento);
        calcularPrecoTotal();
    }


}
