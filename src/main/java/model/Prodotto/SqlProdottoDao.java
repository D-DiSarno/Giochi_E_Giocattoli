package model.Prodotto;
import controller.Http.Condition;
import controller.Http.Operator;
import controller.componenti.Paginator;
import model.Categoria.Categoria;
import model.ConPool.ConPool;
import model.Produttore.Produttore;

import javax.sql.DataSource;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class SqlProdottoDao  implements ProdottoDao<SQLException> {


    //SELECT * FROM %s LIMIT ?,? private String nome;
    //    private int idProdotto;
    //    private double prezzo;
    //    private String descrizione;
    //    private int quantita;;
    public List<Prodotto> fetchProducts(Paginator paginator) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Prodotto LIMIT ?,?  ")) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimite());
              //  System.out.println(paginator.getLimite()+" "+paginator.getOffset());
                ResultSet rs = ps.executeQuery();
                List<Prodotto>prodotti = new ArrayList<>();
                while (rs.next()) {

                    Prodotto prod = new Prodotto();
                    prod.setIdProdotto(rs.getInt("idProdotto"));
                    prod.setNome(rs.getString("nome"));
                    prod.setPrezzo(rs.getDouble("prezzo"));
                    prod.setDescrizione(rs.getString("descrizione"));
                    prod.setQuantita(rs.getInt("quantita_rimanenti"));
                    prod.setEta_minima(rs.getInt("eta_minima"));
                    prod.setImg(rs.getString("img"));
                /*prod.setProduttore(rs.getObject("idProduttore"));
                prod.setCategoria(rs.getObject("idCategoria"));
                 */
                    prodotti.add(prod);
                }
               // System.out.println("post*="+prodotti.size());
                return prodotti;
            }
        }
    }
    public List<Prodotto> fetchProductsLimit(int limite) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                           con.prepareStatement("SELECT * FROM Prodotto LIMIT ?  ")) {
                ps.setInt(1, limite);

                //  System.out.println(paginator.getLimite()+" "+paginator.getOffset());
                ResultSet rs = ps.executeQuery();
                List<Prodotto>prodotti = new ArrayList<>();
                while (rs.next()) {

                    Prodotto prod = new Prodotto();
                    prod.setIdProdotto(rs.getInt("idProdotto"));
                    prod.setNome(rs.getString("nome"));
                    prod.setPrezzo(rs.getDouble("prezzo"));
                    prod.setDescrizione(rs.getString("descrizione"));
                    prod.setQuantita(rs.getInt("quantita_rimanenti"));
                    prod.setEta_minima(rs.getInt("eta_minima"));
                    prod.setImg(rs.getString("img"));
                /*prod.setProduttore(rs.getObject("idProduttore"));
                prod.setCategoria(rs.getObject("idCategoria"));
                 */
                    prodotti.add(prod);
                }
                // System.out.println("post*="+prodotti.size());
                return prodotti;
            }
        }
    }

    //SELECT * FROM %s WHERE email=?;
    public Optional<Prodotto> fetchProduct(int idProdotto) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Prodotto WHERE idProdotto=?")) {
                ps.setInt(1, idProdotto);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = new Prodotto();
                if (rs.next()) {


                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                    prodotto.setImg(rs.getString("img"));

                }

                return Optional.ofNullable(prodotto);
            }
        }
    }

    public boolean createProduct(Prodotto prodotto) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                    con.prepareStatement("INSERT INTO Prodotto (nome,prezzo,descrizione,quantita_rimanenti,eta_minima,img,idProduttore,idCategoria) VALUES(?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, prodotto.getNome());
            ps.setDouble(2, prodotto.getPrezzo());
            ps.setString(3, prodotto.getDescrizione());
            ps.setInt(4, prodotto.getQuantita());
            ps.setInt(5, prodotto.getEta_minima());
                ps.setString(6,prodotto.getImg());
            ps.setString(7,prodotto.getProduttore().getIdProduttore());
            ps.setString(8,prodotto.getCategoria().getIdCategoria());
            System.out.println(prodotto.getImg());

                System.out.println(ps.toString());
                int rows = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            prodotto.setIdProdotto(id);
            System.out.println("id"+ id);

            System.out.println(rows);
            return rows == 1;
        }
    }
    }


    public boolean updateProduct(Prodotto prodotto) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("UPDATE  Prodotto SET nome=?, prezzo=?, descrizione=?, quantita_rimanenti=? , eta_minima=? ,idProduttore=?,idCategoria=? WHERE idProdotto=?;")) {
                ps.setString(1, prodotto.getNome());
                ps.setDouble(2, prodotto.getPrezzo());
                ps.setString(3, prodotto.getDescrizione());
                ps.setInt(4, prodotto.getQuantita());
                ps.setInt(5, prodotto.getEta_minima());
                ps.setString(6,prodotto.getProduttore().getIdProduttore());
                ps.setString(7,prodotto.getCategoria().getIdCategoria());
                ps.setInt(8, prodotto.getIdProdotto());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }


    public boolean deleteProduct(int id) throws SQLException {

        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("DELETE FROM Prodotto WHERE idProdotto=?;")) {
                ps.setInt(1, id);
                System.out.println(ps.toString());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    //Select * from Prodotto as p INNER JOIN categorie as cat INNER JOIN  Produttore as pr ...where
    public List<Prodotto> search(List<Condition> condizioni) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            System.out.println(" in search sql 1");
            String query=ProdottoSearchQuery.search(condizioni);
            System.out.println(query);
            try (PreparedStatement ps =
                         con.prepareStatement(query)) {
                for (int i = 0; i < condizioni.size(); i++) {
                    //indice parte da 0 e set da 1 == i+1
                    if(condizioni.get(i).getOperatore()== Operator.MATCH){
                        System.out.println(i+" "+condizioni.get(i).getValore()+"\n");
                        ps.setObject(i+1,""+condizioni.get(i).getValore()+"");
                    }else {

                        ps.setObject(i + 1, condizioni.get(i).getValore());//passo object e jdbc in automatico genera il set
                        System.out.println("post obj");}
                    }

                System.out.println(" in search sql 2");
                //Select* from Prodotto as pro inner join Categoria C on pro.idCategoria = C.idCategoria inner JOIN Produttore p on pro.idProduttore = p.idProduttore
                //Where pro.nome LIKE %?% AND pro.idCategoria=? AND pro.idProduttore=?
                System.out.println(ps.toString());
                ResultSet set = ps.executeQuery();
                List<Prodotto> prodotti = new ArrayList<>();
                while (set.next()) {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(set.getInt("idProdotto"));
                    prodotto.setNome(set.getString("Nome"));
                    prodotto.setPrezzo(set.getDouble("prezzo"));
                    prodotto.setDescrizione(set.getString("descrizione"));
                    prodotto.setQuantita(set.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(set.getInt("eta_minima"));
                    prodotto.setImg(set.getString("img"));
                    prodotti.add(prodotto);
                }
                System.out.println("size="+prodotti.size());
                return prodotti;
            }
        }
    }


    public int countAll() throws SQLException {
        try (Connection con = ConPool.getConnection()) {

            try (PreparedStatement ps = con.prepareStatement("Select count(*) as totaleProdotti FROM Prodotto ;")){
                ResultSet resultSet=ps.executeQuery();
                int size=0;
                if (resultSet.next()) {
                    size = resultSet.getInt("totaleProdotti");
                }

                return size;
            }
        }
    }
    public int getTotale()throws SQLException{
        try (Connection con = ConPool.getConnection()) {

            try (PreparedStatement ps = con.prepareStatement("Select count(quantita_rimanenti) as magazzino FROM Prodotto ;")){
                ResultSet resultSet=ps.executeQuery();
                int size=0;
                if (resultSet.next()) {
                    size = resultSet.getInt("magazzino");
                }

                return size;
            }
        }
    }


    public Optional<Prodotto> findProdotto(String ricerca) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Prodotto WHERE nome=?")) {
                ps.setString(1, ricerca);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = new Prodotto();
                System.out.println(ps.toString());
                if (rs.next()) {
                      System.out.println("trovato");
                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                }

                return Optional.ofNullable(prodotto);
            }
        }
    }

    public List<Prodotto> searchEta(int minEta, int maxEta) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Prodotto WHERE eta_minima>=? && eta_minima <=?")) {
                ps.setInt(1, minEta);
                ps.setInt(2,maxEta);
                ResultSet rs = ps.executeQuery();
                List<Prodotto>prodotti=new ArrayList<>();
                while (rs.next()) {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                    prodotto.setImg(rs.getString("img"));
                    prodotti.add(prodotto);
                }

                return prodotti;
            }
        }
    }
    public List<Prodotto> searchCategoria(String tipologia) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Prodotto INNER JOIN Categoria on Prodotto.idCategoria=Categoria.idCategoria WHERE categoria.tipologia=?;")) {
                ps.setString(1, tipologia);
                ResultSet rs = ps.executeQuery();
                List<Prodotto> prodotti = new ArrayList<>();
                while (rs.next()) {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                    prodotto.setImg(rs.getString("img"));
                    prodotti.add(prodotto);
                }

                return prodotti;
            }
        }
    }
    public Optional<Prodotto> fetchProductCategoriaProduttore(int idProdotto) throws SQLException {
            try (Connection con = ConPool.getConnection()) {
                try (PreparedStatement ps =
                             con.prepareStatement("SELECT * FROM Prodotto as p INNER JOIN Categoria as c on p.idCategoria=c.idCategoria INNER JOIN Produttore as prod on prod.idProduttore=p.idProduttore WHERE p.idProdotto=?")) {
                    ps.setInt(1, idProdotto);
                    ResultSet rs = ps.executeQuery();
                    Prodotto prodotto = new Prodotto();
                    if (rs.next()) {


                        prodotto.setIdProdotto(rs.getInt("idProdotto"));
                        prodotto.setNome(rs.getString("Nome"));
                        prodotto.setPrezzo(rs.getDouble("prezzo"));
                        prodotto.setDescrizione(rs.getString("descrizione"));
                        prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                        prodotto.setEta_minima(rs.getInt("eta_minima"));
                        prodotto.setImg(rs.getString("img"));

                        Categoria cat=new Categoria();
                        cat.setIdCategoria(rs.getString("idCategoria"));
                        cat.setTipologia(rs.getString("tipologia"));
                        cat.setEtaMinima(rs.getInt("etaMin"));
                        prodotto.setCategoria(cat);

                        Produttore produttore=new Produttore();
                        produttore.setIdProduttore(rs.getString("idProduttore"));
                        produttore.setEmail(rs.getString("mail"));
                        produttore.setNome(rs.getString("nome"));
                        prodotto.setProduttore(produttore);

                    }

                    return Optional.ofNullable(prodotto);
                }
            }
        }

    public boolean decrease(Prodotto prodotto, int quantita) throws SQLException {

        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("UPDATE  Prodotto SET quantita_rimanenti=? WHERE idProdotto=?;")) {

                int rimasto=(prodotto.getQuantita());
                System.out.println(quantita+" dao");
                System.out.println("rimasto="+rimasto);
                        rimasto-=quantita;
                System.out.println("rimangono "+rimasto+" unità da"+prodotto.getQuantita());
                ps.setInt(1,rimasto);
                ps.setInt(2,prodotto.getIdProdotto());
                System.out.println(ps.toString());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }
}



