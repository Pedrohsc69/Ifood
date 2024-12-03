package DAO;

import Conexao.Conexao_BD;
import Entity.Endereco;

import java.sql.PreparedStatement;
import java.util.Objects;

public class EnderecoDAO {

    public void cadastrarEndereco(Endereco endereco){
        String sql = "INSERT INTO ENDERECO (RUA, BAIRRO, CIDADE, ESTADO, COMPLEMENTO, PONTO_REFERENCIA, CEP)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try{
            ps = Objects.requireNonNull(Conexao_BD.getConexao()).prepareStatement(sql);
            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getBairro());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getEstado());
            //ps.setInt(5, endereco.getNumeroCasa());
            ps.setString(5, endereco.getComplemento());
            ps.setString(6, endereco.getPontoReferencia());
            ps.setString(7, endereco.getCep());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
