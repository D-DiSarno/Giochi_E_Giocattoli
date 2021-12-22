package model.Ordine;


import controller.componenti.Paginator;
import model.Carrello.Carrello;
import model.Carrello.CarrelloElementi;
import model.ConPool.ConPool;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDao;
import model.Prodotto.SqlProdottoDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

public class SqlOrdineDao  implements OrdineDao<SQLException> {


    /*public SqlOrdineDao(DataSource dataSource) {
        super(dataSource);
    }
*/
    //SELECT * FROM %s LIMIT ?,?  private int numeroOrdine;


    public List<Ordine> fetchOrders(Paginator paginator) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Ordine LIMIT ?,?")) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimite());

                ResultSet rs = ps.executeQuery();

                List<Ordine> ordini = new ArrayList<>();
                while (rs.next()) {
                    Ordine ord = new Ordine();
                    ord.setNumeroOrdine(rs.getInt("numero_Ordine"));
                    ord.setTotale(rs.getDouble("totale"));
                    //  LocalDate lc=new LocalDate(rs.getDate("dataAcquisto"));
                    Date dataR = (rs.getDate("dataAcquisto"));
                    ord.setDataOrdine(dataR.toLocalDate());
                 //   ord.setQuantita(rs.getInt("quantita"));
                    ord.setVia(rs.getString("via"));
                    ord.setCitta(rs.getString("citta"));
                    ord.setCodice_postale(rs.getString("codice_postale"));
                    ord.setStato_ordine(rs.getString("stato_ordine"));
                    ordini.add(ord);
                }

                return ordini;
            }
        }
    }


    public Optional<Ordine> fetchOrdiniByNumero(int numeroOrdine) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (   PreparedStatement ps =
                    con.prepareStatement("SELECT * from Ordine where numero_Ordine=?;")) {
                ps.setInt(1, numeroOrdine);
                ResultSet rs = ps.executeQuery();
                Ordine ord = new Ordine();
                if (rs.next()) {

                    //ord.setCliente(rs.getInt("idCliente"));
                    ord.setNumeroOrdine(numeroOrdine);
                    ord.setTotale(rs.getDouble("totale"));
                    Date dataR = (rs.getDate("dataAcquisto"));
                    ord.setDataOrdine(dataR.toLocalDate());

                  //  ord.setQuantita(rs.getInt("quantita"));
                    ord.setVia(rs.getString("via"));
                    ord.setCitta(rs.getString("citta"));
                    ord.setCodice_postale(rs.getString("codice_postale"));
                    ord.setStato_ordine(rs.getString("stato_ordine"));
                    System.out.println(ord.getVia());
               /*ord.setCliente();
               ord.setProdotti();
               ord.setStato();*/

                }

                return Optional.ofNullable(ord);
            }
        }
    }
    public Optional<Ordine> fetchOrdiniByStato(String stato) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (  PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Ordine WHERE stato_ordine=?")) {
                ps.setString(1, stato);
                ResultSet rs = ps.executeQuery();
                Ordine ord = new Ordine();
                if (rs.next()) {

                    ord.setNumeroOrdine(rs.getInt("idCliente"));
                    ord.setTotale(rs.getDouble("totale"));
                    Date dataR = (rs.getDate("dataAcquisto"));
                    ord.setDataOrdine(dataR.toLocalDate());
                    ;
                    //ord.setQuantita(rs.getInt("quantita"));
                    ord.setVia(rs.getString("via"));
                    ord.setCitta(rs.getString("citta"));
                    ord.setCodice_postale(rs.getString("codice_postale"));
                    ord.setStato_ordine(rs.getString("stato_ordine"));
                    /* chiamare per ordini il metodo*/

                }

                return Optional.ofNullable(ord);
            }
        }
    }

    public boolean createOrder ( Ordine ordine)throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("INSERT INTO Ordine (dataAcquisto,totale,via,citta,codice_postale,stato_ordine,idCliente) VALUES(?,?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS)) {
                Date dataR = Date.valueOf(ordine.getDataOrdine());
                System.out.println("pre create");
                ps.setDate(1, dataR);
                ps.setDouble(2, ordine.getTotale());
                ps.setString(3, ordine.getVia());
                ps.setString(4, ordine.getCitta());
                ps.setString(5, ordine.getCodice_postale());
                ps.setString(6, ordine.getStato_ordine());
                ps.setInt(7,ordine.getCliente().getIdCliente());

                int rows = ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();

                int id = rs.next() ? rs.getInt(1): -1;
                int i=ordine.getCarrello().getElementi().size();
                SqlProdottoDao prodottoDao =new SqlProdottoDao() ;
              for(int j=0;j<i;j++){
                   createComposizione(id,
                           ordine.getCarrello().getElementi().get(j).getProdotto().getIdProdotto(),
                           ordine.getCarrello().getElementi().get(j).getProdotto().getQuantita());

                   prodottoDao.decrease(ordine.getCarrello().getElementi().get(j).getProdotto(), ordine.getCarrello().getElementi().get(j).getQuantita());

               }

                System.out.println("eseguito 4");
                return rows == 1;
            }
        }
    }
     public boolean createComposizione(int num,int id,int  quanto) throws SQLException {
         try (Connection con = ConPool.getConnection()) {
             try (PreparedStatement ps =
                          con.prepareStatement("INSERT INTO Composizione(numero_Ordine,idProdotto,quantita) values (?,?,?);")) {
                 ps.setInt(1, num);
                 ps.setInt(2, id);
                 ps.setInt(3, quanto);
                 System.out.println(num+" "+id+" "+quanto+""+ps.toString());
                 int rows = ps.executeUpdate();
                 System.out.println("eseguito 3");
                 return rows == 1;
             }
         }
     }

    public boolean updateOrder (Ordine ordine)throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("UPDATE  Ordine SET via=?, citta=?, codice_postale=? , stato_ordine=? , totale=? WHERE numero_Ordine=?;")) {
                ps.setString(1, ordine.getVia());
                ps.setString(2, ordine.getCitta());
                ps.setString(3, ordine.getCodice_postale());
                ps.setString(4, ordine.getStato_ordine());
                ps.setDouble(5, ordine.getTotale());
                ps.setInt(6, ordine.getNumeroOrdine());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
    public boolean updateOrderState (Ordine ordine)throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("UPDATE  Ordine SET stato_ordine=? WHERE numero_Ordine=?;")) {
                ps.setString(1, ordine.getStato_ordine());
                ps.setInt(2, ordine.getNumeroOrdine());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    public boolean deleteOrder (int numeroOrdine) throws SQLException {

        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("DELETE FROM Ordine WHERE numero_Ordine=?;")) {
                ps.setInt(1, numeroOrdine);
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
    public Optional<Ordine> fetchOrdersWithProducts(int numero_ordine) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("Select * from ordine as ord INNER JOIN composizione as comp on ord.numero_Ordine=comp.numero_Ordine INNER JOIN prodotto as prod on comp.idProdotto=prod.idProdotto where ord.numero_Ordine=?")) {
                ps.setInt(1, numero_ordine);

                ResultSet rs = ps.executeQuery();
                List<CarrelloElementi> elementi = new ArrayList<>();
                Ordine ordine = null;
                fetchOrdiniByNumero(numero_ordine);
                while (rs.next()) {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                    int quantita = rs.getInt("quantita");
                    //categoria
                    CarrelloElementi elemento = new CarrelloElementi(prodotto, quantita);
                    elementi.add(elemento);
                }
                Carrello cart = new Carrello(elementi);
                ordine.setCarrello(cart);


                return Optional.ofNullable(ordine);
            }
        }
    }
    public int countAll () throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("Select count(*) as totaleOrdini FROM Ordine ;")) {
                ResultSet resultSet = ps.executeQuery();
                int size = 0;
                if (resultSet.next()) {
                    size = resultSet.getInt("totaleOrdini");
                }
                System.out.println(size);
                return size;
            }
        }
    }

    public double getTotaleIncasso()throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("Select * FROM Ordine ;")) {
                ResultSet resultSet = ps.executeQuery();


                double incasso = 0;
                while (resultSet.next()) {

                        incasso += resultSet.getDouble("totale");

                }

                return incasso;
            }
        }
    }
    public List<Ordine> fetchOrdersWithProductsList(int numero_ordine) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("Select * from ordine as ord INNER JOIN composizione as comp on ord.numero_Ordine=comp.numero_Ordine INNER JOIN prodotto as prod on comp.idProdotto=prod.idProdotto where ord.numero_Ordine=?")) {
                ps.setInt(1, numero_ordine);

                ResultSet rs = ps.executeQuery();
                List<CarrelloElementi> elementi = new ArrayList<>();
               List<Ordine>ordini = new ArrayList<>();
                Prodotto prodotto = new Prodotto();
                CarrelloElementi elemento;
                int quantita=0;
                while (rs.next()) {
                    Ordine ord = new Ordine();
                    ord.setNumeroOrdine(rs.getInt("numero_Ordine"));
                    ord.setTotale(rs.getDouble("totale"));
                    //  LocalDate lc=new LocalDate(rs.getDate("dataAcquisto"));
                    Date dataR = (rs.getDate("dataAcquisto"));
                    ord.setDataOrdine(dataR.toLocalDate());
                    //   ord.setQuantita(rs.getInt("quantita"));
                    ord.setVia(rs.getString("via"));
                    ord.setCitta(rs.getString("citta"));
                    ord.setCodice_postale(rs.getString("codice_postale"));
                    ord.setStato_ordine(rs.getString("stato_ordine"));



                    prodotto.setIdProdotto(rs.getInt("idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                    rs.getInt("quantita");
                    //categoria
                    elemento = new CarrelloElementi(prodotto, quantita);
                    elementi.add(elemento);
                    Carrello cart = new Carrello(elementi);
                    ord.setCarrello(cart);
                    ordini.add(ord);
                }


                return ordini;
            }
        }
    }
    public List<Ordine> fetchOrdersWithProductsListByCliente(int id) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("Select * from ordine as ord INNER JOIN composizione as comp on ord.numero_Ordine=comp.numero_Ordine INNER JOIN prodotto as prod on comp.idProdotto=prod.idProdotto where ord.idCliente=?")) {
                ps.setInt(1, id);
                System.out.println(ps.toString());
                ResultSet rs = ps.executeQuery();
                List<CarrelloElementi> elementi = new ArrayList<>();
                List<Ordine>ordini = new ArrayList<>();
                Prodotto prodotto = new Prodotto();
                CarrelloElementi elemento;

                int quantita=0;
                while (rs.next()) {
                    Ordine ord = new Ordine();
                    ord.setNumeroOrdine(rs.getInt("numero_Ordine"));
                    ord.setTotale(rs.getDouble("totale"));
                    //  LocalDate lc=new LocalDate(rs.getDate("dataAcquisto"));
                    Date dataR = (rs.getDate("dataAcquisto"));
                    ord.setDataOrdine(dataR.toLocalDate());
                    //   ord.setQuantita(rs.getInt("quantita"));
                    ord.setVia(rs.getString("via"));
                    ord.setCitta(rs.getString("citta"));
                    ord.setCodice_postale(rs.getString("codice_postale"));
                    ord.setStato_ordine(rs.getString("stato_ordine"));


                    prodotto.setIdProdotto(rs.getInt("prod.idProdotto"));
                    prodotto.setNome(rs.getString("Nome"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setQuantita(rs.getInt("quantita_rimanenti"));
                    prodotto.setEta_minima(rs.getInt("eta_minima"));
                    quantita=rs.getInt("quantita");
                    //categoria
                    elemento = new CarrelloElementi(prodotto, quantita);
                    elementi.add(elemento);
                    Carrello cart = new Carrello(elementi);
                    ord.setCarrello(cart);
                    ordini.add(ord);
                }


                return ordini;
            }
        }
    }
}
