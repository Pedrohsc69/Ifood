package Entity;

import java.util.List;
import java.math.BigDecimal;

public class Pedido extends Base {

    private String nome;
    private String descricao;
    private double preco;
    private Categoria categoria;
    private Restaurante restaurante;
    private List<Acompanhamento> acompanhamento;

    Pedido(String nome, String descricao, double preco, Categoria categoria, Restaurante restaurante){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.restaurante = restaurante;
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

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public List<Acompanhamento> getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(List<Acompanhamento> acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}
