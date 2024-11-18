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

    public void exibirCarrinho() {
        StringBuilder sb = new StringBuilder("Carrinho:\n");
        for (ItemPedido item : itens) {
            sb.append("Restaurante: ").append(item.getRestaurante().getNome()).append("\n");
            sb.append("-----Produto: ").append(item.getProduto().getNome())
                    .append("\n Quantidade: ").append(item.getQuantidade())
                    .append("\n Preço: R$").append(item.getPrecoTotal()).append("\n");
            if (!item.getAcompanhamentos().isEmpty()) {
                sb.append("-----Acompanhamentos: \n");
                for (Acompanhamento acompanhamento : item.getAcompanhamentos()) {
                    sb.append("- ").append(acompanhamento.getNome())
                            .append("\n Quantidade: ").append(acompanhamento.getQuantidade())
                            .append("\n Preço: R$").append(acompanhamento.getValor_total()).append("\n");
                }
            }
        }
        sb.append("Valor Total: R$").append(valorTotal).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
