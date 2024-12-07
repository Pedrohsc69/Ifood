package Entity;

import java.util.ArrayList;
import java.util.List;

public class Associacao_Rest_Prod {
    private Restaurante restaurante;
    private List<Produto> produtos;

    public Associacao_Rest_Prod(Restaurante restaurante) {
        this.restaurante = restaurante;
        this.produtos = new ArrayList();
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public String getNome_Restaurante(){
        return restaurante.getNome();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void Add_Produto(Produto produto) {
        produtos.add(produto);
    }

    public void Exibir_Produtos() {
        System.out.println("\nProdutos do restaurante '" +restaurante.getNome() + "':");
        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println();
        }
    }
}

