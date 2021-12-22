package model.Produttore;


import controller.componenti.Paginator;
import model.ConPool.ConPool;
import model.Ordine.Ordine;
import model.Prodotto.Prodotto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class SqlProduttoreDao  implements ProduttoreDao<SQLException> {


    //SELECT * FROM %s LIMIT ?,?
    // private String IdProduttore;
    //    private String nome;
    //    private String email;
    //    private List<Prodotto> prodotti;;
    public List<Produttore> fetchProducers(Paginator paginator) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Produttore LIMIT ?,?")) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimite());
                ResultSet rs = ps.executeQuery();
                List<Produttore> produttori = new ArrayList<>();
                while (rs.next()) {

                    Produttore prod = new Produttore();
                    prod.setIdProduttore(rs.getString("idProduttore"));
                    prod.setEmail(rs.getString("mail"));
                    prod.setNome(rs.getString("nome"));
               /*
               prod.setProdotti()
                */

                    produttori.add(prod);
                }
                System.out.println("post");
                return produttori;
            }
        }
    }
    public List<Produttore> fetchProducersAll() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Produttore ")) {
                ResultSet rs = ps.executeQuery();
                List<Produttore> produttori = new ArrayList<>();
                while (rs.next()) {

                    Produttore prod = new Produttore();
                    prod.setIdProduttore(rs.getString("idProduttore"));
                    prod.setEmail(rs.getString("mail"));
                    prod.setNome(rs.getString("nome"));
               /*
               prod.setProdotti()
                */

                    produttori.add(prod);
                }
               // System.out.println("post");
                return produttori;
            }
        }
    }

    //SELECT * FROM %s WHERE email=?;
    public Optional<Produttore> fetchProducer(String id) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (   PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Produttore WHERE idProduttore=?")) {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                Produttore produttore = new Produttore();
                if (rs.next()) {

                    produttore.setIdProduttore(rs.getString("idProduttore"));
                    produttore.setEmail(rs.getString("mail"));
                    produttore.setNome(rs.getString("nome"));

                }

                return Optional.ofNullable(produttore);
            }
        }
    }

    public boolean createProducer(Produttore produttore) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("INSERT INTO Produttore (nome,mail,idProduttore) VALUES(?,?,?);")) {
                ps.setString(1, produttore.getNome());
                ps.setString(2, produttore.getEmail());
                ps.setString(3, produttore.getIdProduttore());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    public boolean updateProduces(Produttore produttore,String id) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("UPDATE  Produttore SET idProduttore=?,nome=?, mail=? WHERE idProduttore=?;")) {
                ps.setString(1,produttore.getIdProduttore());
                ps.setString(2, produttore.getNome());
                ps.setString(3, produttore.getEmail());
                ps.setString(4, id);
                System.out.println(ps.toString());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }


    public boolean deleteProducer(String idProduttore) throws SQLException {

        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("DELETE FROM Produttore WHERE idProduttore=?;")) {
                ps.setString(1, idProduttore);
                System.out.println(ps.toString());
                int rows = ps.executeUpdate();
                System.out.println(rows);
                return rows == 1;
            }
        }
    }
    public Optional<Produttore> fetchProducersWithProducts(String idProduttore) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("Select * from Produttore as prod INNER JOIN prodotto pro on prod.idProduttore = pro.idProduttore where prod.idProduttore=?")) {
                ps.setString(1, idProduttore);
                ResultSet rs = ps.executeQuery();
                List<Prodotto> prodottiByProduttore = new ArrayList<>();
                Produttore produttore = null;
               Optional<Produttore>produttoreOptional=fetchProducer(idProduttore);
                if (rs.next()) {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                    prodottiByProduttore.add(prodotto);
                }
                produttore.setProdotti(prodottiByProduttore);


            return Optional.ofNullable(produttore);
        }
    }
    }

    public int countAll () throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("Select count(*) as totaleProduttori FROM Produttore ;")) {
                ResultSet resultSet = ps.executeQuery();
                int size = 0;
                if (resultSet.next()) {
                    size = resultSet.getInt("totaleProduttori");
                }

                return size;
            }
        }
    }
}

