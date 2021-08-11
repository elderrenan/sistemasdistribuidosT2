/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CDB;

import Entidades.Hospedagem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

/**
 *
 * @author Eldrey
 */
public class CDBHospedagem {

    /**
     * Método que lê as hospedagens no banco de dados e retorna em uma lista
     *
     * @return
     */
    public List<Hospedagem> getLista() {
        List<Hospedagem> listHosp = new ArrayList<>();
        Connection con = Conectar.getConnection();
        String query = "SELECT * FROM hospedagem;";
        ResultSet rs;
        try {
            CallableStatement stmt = con.prepareCall(query);
            rs = stmt.executeQuery();

            while (rs.next()) {

                listHosp.add(new Hospedagem(rs.getInt("codigo"), rs.getString("nome").replace("  ", ""), rs.getString("cidade").replace("  ", ""), rs.getString("estado").replace("  ", ""),
                        rs.getInt("numQuartosDisponiveis"), rs.getInt("valorDiaria"), rs.getString("periodoInicio").replace(" ", ""), rs.getString("periodoFim").replace(" ", "")));
//                System.out.println(rs.getInt("codigo")+" "+ rs.getString("nome")+" "+ rs.getString("cidade")+" "+ rs.getString("estado")+" "+
//                        rs.getInt("numQuartosDisponiveis")+" "+ rs.getInt("valorDiaria")+" "+ rs.getString("periodoInicio")+" "+ rs.getString("periodoFim"));
            }
            stmt.close();

            return listHosp;
        } catch (Exception e) {
            System.out.println("ERRO BD LISTA HOSPEDAGEM");
            e.printStackTrace();
            return null;
        }
    }

    public void efetuaReserva(Hospedagem hosp, int numQuartos) {

        Connection con = Conectar.getConnection();
        String query = " UPDATE hospedagem SET numquartosdisponiveis=" + (hosp.getNumQuartoDisponivel() - numQuartos) + " WHERE codigo = ?;";

        try {
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, hosp.getCodigo());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO BD UPDATE HOSPEDAGEM");
            e.printStackTrace();
        }
    }

}
