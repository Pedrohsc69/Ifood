public class Pedido_Produto_Acompanhamento extends Base{

    private Pedido_Produto pedidoProduto;
    private Acompanhamento acompanhamento;


    public Pedido_Produto getPedidoProduto() {
        return pedidoProduto;
    }

    public void setPedidoProduto(Pedido_Produto pedidoProduto) {
        this.pedidoProduto = pedidoProduto;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}
