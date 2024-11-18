import java.sql.Timestamp;

public class Historico_Entrega extends Base{

    private Status_Entrega statusEntrega;
    private Pedido pedido;
    private Timestamp dataHora;


    public Status_Entrega getStatusEntrega() {
        return statusEntrega;
    }
    public void setStatusEntrega(Status_Entrega statusEntrega) {
        this.statusEntrega = statusEntrega;
    }


    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    public Timestamp getDataHora() {
        return dataHora;
    }
    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }
}
