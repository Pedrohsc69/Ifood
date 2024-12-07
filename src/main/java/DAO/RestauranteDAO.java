package DAO;
import Conexao.Conexao_BD;
import Entity.Restaurante;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RestauranteDAO {

    //public void cadastrarRestaurante(Restaurante restaurante){
       // String sql = "INSERT INTO RESTAURANTE (NOME, TELEFONE) VALUES (?, ?)";

        //PreparedStatement ps = null;
        //try {
        //    ps = Objects.requireNonNull(Conexao_BD.getConexao()).prepareStatement(sql);
        //    ps.setString(1, restaurante.getNome());
        //    //ps.setInt(2, restaurante.getEnderecoId());
        //    ps.setString(2, restaurante.getTelefone());

        //    ps.execute();
        //    ps.close();

        //} catch (SQLException e) {
        //    e.printStackTrace();
        //}

    //}

    public void buscarRestaurante(){
        Connection conexao = Conexao_BD.getConexao();

        if (conexao != null){
            try{
                String query = "SELECT * FROM RESTAURANTE";
                Statement statement = conexao.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");

                    System.out.println("ID: " + id);
                    System.out.println("NOME: " + nome);
                }

                resultSet.close();
                statement.close();
                conexao.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
