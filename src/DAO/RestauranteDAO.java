package DAO;
import Conexao.Conexao_BD;
import Entity.Restaurante;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class RestauranteDAO {

    public void cadastrarRestaurante(Restaurante restaurante){
        String sql = "INSERT INTO RESTAURANTE (NOME, TELEFONE) VALUES (?, ?)";

        PreparedStatement ps = null;
        try {
            ps = Objects.requireNonNull(Conexao_BD.getConexao()).prepareStatement(sql);
            ps.setString(1, restaurante.getNome());
            //ps.setInt(2, restaurante.getEnderecoId());
            ps.setString(2, restaurante.getTelefone());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
