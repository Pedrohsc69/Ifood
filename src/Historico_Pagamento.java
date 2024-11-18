import java.sql.Date;
import java.math.BigDecimal;

public class Historico_Pagamento extends Base{

    private Date dataPaga;
    private Forma_Pagamento forma_paga;
    private BigDecimal valor;


    public Date getDataPaga() {
        return dataPaga;
    }
    public void setDataPaga(Date dataPaga) {
        this.dataPaga = dataPaga;
    }


    public Forma_Pagamento getForma_paga() {
        return forma_paga;
    }
    public void setForma_paga(Forma_Pagamento forma_paga) {
        this.forma_paga = forma_paga;
    }


    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
