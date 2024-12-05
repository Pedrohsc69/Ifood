package Entity;

import java.util.List;

public class Pedido extends Base {

    private String nome;
    private String descricao;
    private double precoProd;
    private Categoria categoria;
    private Restaurante restaurante;
    private List<Acompanhamento> acompanhamento;

    Pedido(String nome, String descricao, double preco, Categoria categoria, Restaurante restaurante){
        this.nome = nome;
        this.descricao = descricao;
        this.precoProd = preco;
        this.categoria = categoria;
        this.restaurante = restaurante;
    }

    public String getNome() {
        return nome;
    }


    public String getDescricao() {
        return descricao;
    }

    public double getPrecoProd() {
        return precoProd;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public String getNomeRestaurante(){
        return getRestaurante().getNome();
    }

    public List<Acompanhamento> getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(List<Acompanhamento> acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}
