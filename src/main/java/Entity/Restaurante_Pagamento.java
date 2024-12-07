package Entity;

public class Restaurante_Pagamento extends Base{

    private Restaurante restaurante;
    private Forma_Pagamento pagamento;


    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }


    public Forma_Pagamento getPagamento() {
        return pagamento;
    }
    public void setPagamento(Forma_Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
