package Entity;

import java.util.List;
import java.math.BigDecimal;

public class Pedido extends Base {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private Restaurante restaurante;
    private List<Acompanhamento> acompanhamento;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
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
