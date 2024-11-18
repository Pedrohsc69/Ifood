import java.math.BigDecimal;
import java.util.List;

public class Produto extends Base {

    private String nome;
    private String descricao;
    private double preco;
    private Categoria categoria;
    private Restaurante restaurante;
    private List<Acompanhamento> acompanhamento;

    Produto(String nome, String descricao, double preco, int id){
        setId(id);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }


    public String getNome() {
        return nome;
    }


    public String getDescricao() {
        return descricao;
    }


    public double getPreco() {
        return preco;
    }


    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }


    public List<Acompanhamento> getAcompanhamento() {
        return acompanhamento;
    }
    public void setAcompanhamento(List<Acompanhamento> acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}
