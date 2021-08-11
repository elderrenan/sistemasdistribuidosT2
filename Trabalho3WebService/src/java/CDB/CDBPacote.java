/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CDB;

import Entidades.Pacote;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eldrey
 */
public class CDBPacote {

    /**
     * Método que lê as hospedagens no banco de dados e retorna em uma lista
     *
     * @return
     */
    public List<Pacote> getLista() {
        List<Pacote> listPac = new ArrayList<>();
        Connection con = Conectar.getConnection();
        String query = "SELECT * FROM pacotes;";
        ResultSet rs;
        try {
            CallableStatement stmt = con.prepareCall(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                listPac.add(new Pacote(rs.getInt("codigo"), rs.getInt("codhospedagen"), rs.getInt("codpassagemida"),
                        rs.getInt("codpassagemvolta"), rs.getDouble("valorpacote"), rs.getInt("vagasDisponíveis"),
                        rs.getString("datapartida").replace("  ", ""), rs.getString("dataretorno").replace("  ", ""),
                        rs.getString("estadoorigem").replace("  ", ""), rs.getString("cidadeorigem").replace("  ", ""),
                        rs.getString("estadodestio").replace("  ", ""), rs.getString("cidadedestino").replace("  ", "")));

            }
            stmt.close();

            return listPac;
        } catch (Exception e) {
            System.out.println("ERRO BD LISTA PACOTE");
            e.printStackTrace();
            return null;
        }
    }

    public void efetuaReserva(Pacote pac, int numPessoas, int hospUp, int passIdaUp, int passVoltUp) {

        Connection con = Conectar.getConnection();
        String query = " UPDATE pacotes SET vagasdisponíveis=" + (pac.getVagasDisponíveis() - numPessoas) + " WHERE codigo = ?;";

        try {
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, pac.getCodigo());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO BD UPDATE  PACOTE");
            e.printStackTrace();
        }
        query = " UPDATE passagens SET quantpassagemdisponivel=" + passIdaUp + " WHERE codigo = ?;";

        try {
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, pac.getCodPassagemIda());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO BD UPDATE PASSAGEM");
            e.printStackTrace();
        }
        query = " UPDATE passagens SET quantpassagemdisponivel=" + passVoltUp + " WHERE codigo = ?;";

        try {
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, pac.getCodPassagemVolta());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO BD UPDATE PASSAGEM");
            e.printStackTrace();
        }
        query = " UPDATE hospedagem SET numquartosdisponiveis=" + hospUp + " WHERE codigo = ?;";

        try {
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, pac.getCodHospedagen());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO BD UPDATE HOSPEDAGEM");
            e.printStackTrace();
        }
    }

}
