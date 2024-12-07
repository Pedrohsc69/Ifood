package Entity;

public class Avaliacao extends Base{
    private int nota;
    private Pedido pedido;
    private Restaurante restaurante;


    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }


    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
