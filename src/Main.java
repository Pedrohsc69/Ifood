import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        List<Associacao_Rest_Prod> List_Rest_Prod = new ArrayList<>();
        List<Associacao_Prod_Acomp> List_Prod_Acomp = new ArrayList<>();

        // CRIAÇÃO DO RESTAURANTE 1 (rest1)
        Endereco end_rest1 = new Endereco("Rua Graciliano", "Centro", "Petrolândia",
                156, "Próximo ao banco Caixa", "56984-000");

        Restaurante rest1 = new Restaurante("Bom Papo", end_rest1, "(87) 9 9636-5452");

        Produto prod1_rest1 = new Produto("Hamburguer",
                "Carne de hamburguer, tomate, alface e cebola", 9.99, 1);
        Produto prod2_rest1 = new Produto("Pizza de Calabresa",
                "Calabresa, Queijo mussarela e Cebola", 24.99, 2);

        Acompanhamento acompa_prod1_rest1 = new Acompanhamento("Maionese caseira", 2.50,
                "Maionese, alho, cebola e creme de leite", 1);
        Acompanhamento acompa_prod2_rest1 = new Acompanhamento("Batata Frita", 15,
                "Batata Frita com cheddar e bacon", 2);

        Associacao_Rest_Prod assoc1 = new Associacao_Rest_Prod(rest1);
        Associacao_Prod_Acomp associacaoProdAcomp1 = new Associacao_Prod_Acomp(prod1_rest1);
        Associacao_Prod_Acomp associacaoProdAcomp2 = new Associacao_Prod_Acomp(prod2_rest1);

        // ADICIONANDO INFORMAÇÕES DO RESTAURANTE 1
        assoc1.Add_Produto(prod1_rest1);
        assoc1.Add_Produto(prod2_rest1);
        associacaoProdAcomp1.Add_Acomp(acompa_prod1_rest1);
        associacaoProdAcomp2.Add_Acomp(acompa_prod2_rest1);

        // CRIAÇÃO DO RESTAURANTE 2 (rest2)
        Endereco end_rest2 = new Endereco("Rua Graciliano", "Centro", "Petrolândia",
                156, "Próximo ao banco Caixa", "56984-000");

        Restaurante rest2 = new Restaurante("Bonapeti", end_rest2, "(87) 9 7896-4563");

        Produto prod1_rest2 = new Produto("Macarronada",
                "Macarrão, Molho branco e Calabresa", 25, 1);
        Produto prod2_rest2 = new Produto("Pastel de Frango",
                "Frango desfiado, Queijo cheddar, Bacon e Salada", 15, 2);

        //Acompanhamento acompa_prod2_rest2 = new Acompanhamento("Maionese caseira",
       //         "Maionese, alho, cebola e creme de leite", 2);
        Associacao_Rest_Prod assoc2 = new Associacao_Rest_Prod(rest2);

        // ADICIONANDO INFORMAÇÕES DO RESTAURANTE 2
        assoc2.Add_Produto(prod1_rest2);
        assoc2.Add_Produto(prod2_rest2);


        // ADICIONANDO INFORMAÇÕES NAS LISTAS DE ASSOCIAÇÕES
        //LIST REST PROD
        List_Rest_Prod.add(assoc1);
        List_Rest_Prod.add(assoc2);
        //LIS PROD ACOMP
        List_Prod_Acomp.add(associacaoProdAcomp1);
        List_Prod_Acomp.add(associacaoProdAcomp2);


        Menu_Inicial menu = new Menu_Inicial(List_Rest_Prod, List_Prod_Acomp);
        menu.Exibir_Menu();



    }
}
