package Entity; // Certifique-se de que o pacote está correto

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.util.Objects;
import javax.swing.*;
import java.awt.*;
import java.util.List;


@Entity
@Table(name = "historico_pedido")
public class Historico_Pedido extends Base{

    @Enumerated(EnumType.STRING)
    private Status_Entrega statusEntrega;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;


    private Timestamp dataHora;

    Historico_Pedido(){

    }

    Historico_Pedido(Status_Entrega statusEntrega, Pedido pedido, Timestamp dataHora){
        this.statusEntrega = statusEntrega;
        this.pedido = pedido;
        this.dataHora = dataHora;
    }

    public Status_Entrega getStatusEntrega() {
        return statusEntrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    @Override
    public String toString(){
        return "HistoricoPedido{" +
                "statusEntrega=" + statusEntrega +
                ", pedido=" + pedido +
                ", dataHora=" + dataHora +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historico_Pedido that = (Historico_Pedido) o;
        return statusEntrega == that.statusEntrega && Objects.equals(pedido, that.pedido)
                && Objects.equals(dataHora, that.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusEntrega, pedido, dataHora);
    }

    public static List<Historico_Pedido> obterTodosPedidos() {
        EntityManager em = Persistence.createEntityManagerFactory("pu")
                .createEntityManager();
        return em.createQuery("SELECT h FROM Historico_Pedido h", Historico_Pedido.class).getResultList();
    }

    public static void salvarPedido(Status_Entrega statusEntrega, Pedido pedido, Timestamp dataHora) {
        EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
        em.getTransaction().begin();
        Historico_Pedido historicoPedido = new Historico_Pedido(statusEntrega, pedido, dataHora);
        em.persist(historicoPedido);
        em.getTransaction().commit();
        em.close();
    }


    public static void exibirHistoricoPedido(){
        JFrame frame = new JFrame("Histórico de Pedidos");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        List<Historico_Pedido> pedidos = Historico_Pedido.obterTodosPedidos();
        StringBuilder historico = new StringBuilder(); for (Historico_Pedido pedido : pedidos) {
            historico.append(pedido.toString()).append("\n\n");
        }
        textArea.setText(historico.toString());

        frame.setVisible(true);
    }

}
