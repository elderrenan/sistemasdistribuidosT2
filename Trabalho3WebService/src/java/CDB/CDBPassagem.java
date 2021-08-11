/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CDB;

import Entidades.Passagem;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eldrey
 */
public class CDBPassagem {

    /**
     * Método que lê as hospedagens no banco de dados e retorna em uma lista
     *
     * @return
     */
    public List<Passagem> getLista() {
        List<Passagem> listPass = new ArrayList<>();
        Connection con = Conectar.getConnection();
        String query = "SELECT * FROM passagens;";
        ResultSet rs;
        try {
            CallableStatement stmt = con.prepareCall(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                listPass.add(new Passagem(rs.getInt("codigo"), rs.getString("estadoorigem").replace("  ", ""),
                        rs.getString("cidadeorigem").replace("  ", ""), rs.getString("estadodestino").replace("  ", ""),
                        rs.getString("cidadedestino").replace("  ", ""), rs.getString("compania").replace("  ", ""),
                        rs.getString("datainicio").replace("  ", ""), rs.getString("datafim").replace("  ", ""),
                        rs.getInt("quantpassagemdisponivel"), rs.getDouble("valor")));

            }
            stmt.close();

            return listPass;
        } catch (Exception e) {
            System.out.println("ERRO BD LISTA PASSAGEM");
            e.printStackTrace();
            return null;
        }
    }

    public void efetuaReserva(Passagem pass, int numPassagens) {

        Connection con = Conectar.getConnection();
        String query = " UPDATE passagens SET quantpassagemdisponivel=" + (pass.getQuantPassagemDisponivel() - numPassagens) + " WHERE codigo = ?;";

        try {
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, pass.getCodigo());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO BD UPDATE PASSAGEM");
            e.printStackTrace();
        }
    }

}
