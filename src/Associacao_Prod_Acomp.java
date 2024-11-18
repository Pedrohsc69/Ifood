import java.util.ArrayList;
import java.util.List;

public class Associacao_Prod_Acomp {
    private Produto produto;
    private List<Acompanhamento> acompanhamentos;

    Associacao_Prod_Acomp(Produto produto){
        this.produto = produto;
        this.acompanhamentos = new ArrayList<>();
    }

    public Produto getProduto(){
        return produto;
    }

    public List<Acompanhamento> getAcompanha(){
        return acompanhamentos;
    }


    public void Add_Acomp(Acompanhamento acompanha){
        acompanhamentos.add(acompanha);
    }

    public void Exibir_Acomps(){
        System.out.println("\nAcompanhamentos do produto '" +produto.getNome() + "':");
        for (Acompanhamento acomps : acompanhamentos) {
            System.out.println("ID: " + acomps.getId());
            System.out.println("Nome: " + acomps.getNome());
            System.out.println("Descrição: " + acomps.getDescricao());
            System.out.printf("Preço: R$%.2f %n", (acomps.getValor()));
            System.out.println();
        }
    }

}
