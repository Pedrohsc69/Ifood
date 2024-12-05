package Entity;

import java.util.List;

public class Acompanhamento extends Base {
    private String nome;
    private String descricao;
    private double valor;
    private List<Produto> produtos;
    private int quantidade;
    private double valor_total;

    Acompanhamento( int id, String nome, double valor, String descricao){
        setId(id);
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.quantidade = 1;
        this.valor_total = valor;
    }

    public String getNome() {
        return nome;
    }


    public String getDescricao() {
        return descricao;
    }


    public double getValor() {
        return valor;
    }


    public int getQuantidade(){
        return quantidade;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
        this.valor_total = valor * quantidade;
    }

    public void setValor_total(double valor_total){
        this.valor_total = valor_total;
    }

    public double getValor_total(){
        return valor_total;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
