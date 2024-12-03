package Entity;

import java.util.List;

public class Restaurante extends Base {

    private String nome;
    private Endereco endereco;
    private String telefone;
    private Categoria categoria;
    private Horario_Funcionamento horarioFunc;
    private boolean isRetirada;
    private List<Produto> produtos;

    Restaurante(int id,String nome, Endereco endereco, String telefone) {
        setId(id);
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    Restaurante(int id, String nome){
        setId(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getEnderecoId() {
        return endereco.getId();
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public Horario_Funcionamento getHorarioFunc() {
        return horarioFunc;
    }

    public void setHorarioFunc(Horario_Funcionamento horarioFunc) {
        this.horarioFunc = horarioFunc;
    }


    public boolean isRetirada() {
        return isRetirada;
    }

    public void setRetirada(boolean isRetirada) {
        this.isRetirada = isRetirada;
    }

    public void Add_Produtos(Produto produto) {
        produtos.add(produto);
    }

    public void getProdutos() {
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println();
        }
    }


}
