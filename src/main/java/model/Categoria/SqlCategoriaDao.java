package model.Categoria;

import controller.componenti.Paginator;
import model.Cliente.Cliente;
import model.ConPool.ConPool;

import model.Prodotto.Prodotto;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlCategoriaDao implements CategoriaDao<SQLException> {


    public boolean createCategory(Categoria categoria) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (   PreparedStatement ps =
                    con.prepareStatement("INSERT INTO Categoria (idCategoria,etaMin,tipologia) VALUES(?,?,?);",Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, categoria.getIdCategoria());
                ps.setDouble(2, categoria.getEtaMinima());
                ps.setString(3, categoria.getTipologia());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    public Categoria fetchCategory(String categoriaID) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Categoria WHERE idCategoria=?")) {
                ps.setString(1, categoriaID);
                ResultSet rs = ps.executeQuery();
                Categoria cat = new Categoria();
                if (rs.next()) {

                    cat.setIdCategoria(rs.getString("idCategoria"));
                    cat.setTipologia(rs.getString("tipologia"));
                    cat.setEtaMinima(rs.getInt("etaMin"));

                }

                return cat;
            }
        }
    }

    public List<Categoria> fetchCategories(Paginator paginator) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Categoria LIMIT ?,?")) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimite());
                ResultSet rs = ps.executeQuery();
                List<Categoria> categorie = new ArrayList<>();
                while (rs.next()) {
                    Categoria cat = new Categoria();
                    cat.setIdCategoria(rs.getString("idCategoria"));
                    cat.setTipologia(rs.getString("tipologia"));
                    cat.setEtaMinima(rs.getInt("etaMin"));
                    categorie.add(cat);
                }

                return categorie;
            }
        }
    }
    public List<Categoria> fetchCategoriesAll() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                           con.prepareStatement("SELECT * FROM Categoria")) {

                ResultSet rs = ps.executeQuery();
                List<Categoria> categorie = new ArrayList<>();
                while (rs.next()) {
                    Categoria cat = new Categoria();
                    cat.setIdCategoria(rs.getString("idCategoria"));
                    cat.setTipologia(rs.getString("tipologia"));
                    cat.setEtaMinima(rs.getInt("etaMin"));
                    categorie.add(cat);
                }
               // System.out.println("qui in sql"+categorie.get(0).getIdCategoria());

                return categorie;
            }
        }
    }
    public Optional<Categoria> fetchCategories(String id) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Categoria WHERE idCategoria=?")) {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                Categoria cat = new Categoria();
                if (rs.next()) {

                    cat.setIdCategoria(rs.getString("idCategoria"));
                    cat.setTipologia(rs.getString("tipologia"));
                    cat.setEtaMinima(rs.getInt("etaMin"));
                }

                return Optional.ofNullable(cat);
            }
        }
    }

    public Optional<Categoria> fetchCategoryAge(int eta) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Categoria WHERE etaMin>=?")) {
                ps.setInt(1, eta);
                ResultSet rs = ps.executeQuery();
                Categoria cat = new Categoria();
                if (rs.next()) {

                    cat.setIdCategoria(rs.getString("idCategoria"));
                    cat.setTipologia(rs.getString("tipologia"));
                    cat.setEtaMinima(rs.getInt("etaMin"));

                }

                return Optional.ofNullable(cat);
            }
        }
    }

    public boolean updateCategory(Categoria categoria) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (    PreparedStatement ps =
                    con.prepareStatement("UPDATE  Categoria SET etaMin= ?,tipologia= ? WHERE idCategoria=?;")){
            ps.setInt(1, categoria.getEtaMinima());
            ps.setString(2, categoria.getTipologia());
            ps.setString(3, categoria.getIdCategoria());

            int rows = ps.executeUpdate();
            return rows == 1;
        }
     }
    }

    public boolean deleteCategory(String idCategoria) throws SQLException {

        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("DELETE FROM Categoria WHERE idCategoria=?;")) {
                ps.setString(1, idCategoria);
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    public Optional<Categoria> fetchCategoryWithProducts(String categoriaId) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("Select * from categoria as cat INNER JOIN prodotto pro on cat.idCategoria = pro.idCategoria where cat.idCategoria=?")) {
                ps.setString(1, categoriaId);
                ResultSet rs = ps.executeQuery();
                List<Prodotto> prodotti = new ArrayList<>();
                Categoria categoria = null;
                categoria = fetchCategory(categoriaId);
                if (rs.next()) {

                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    prodotti.add(prodotto);

                }
                categoria.setProdotti(prodotti);


                return Optional.ofNullable(categoria);
            }
        }
    }

    public Optional<Categoria> fetchCategoryWithClients(String categoriaId) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("Select * from cliente as cl INNER JOIN Preferenza as pref on cl.idCliente = pref.idCliente  INNER JOIN categoria as cat on   pref.idCategoria =cat.idCategoria where cat.idCategoria=?")) {
                ps.setString(1, categoriaId);
                ResultSet rs = ps.executeQuery();
                List<Cliente> clienti = new ArrayList<>();
                Categoria categoria = null;
                categoria = fetchCategory(categoriaId);
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setEmail(rs.getString("mail"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCognome(rs.getString("cognome"));
                    cliente.setVia(rs.getString("via"));
                    cliente.setCitta(rs.getString("citta"));
                    cliente.setCodice_postale(rs.getString("codice_postale"));
                    cliente.setRuolo(rs.getBoolean("ruolo"));
                    clienti.add(cliente);
                }
                categoria.setClienti(clienti);

                return Optional.ofNullable(categoria);
            }
        }
    }


    public int countAll () throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("Select count(*) as totaleCategorie FROM Categoria ;")) {
                ResultSet resultSet = ps.executeQuery();
                int size = 0;
                if (resultSet.next()) {
                    size = resultSet.getInt("totaleCategorie");
                }
               System.out.println(size);
                return size;
            }
        }
    }
}


