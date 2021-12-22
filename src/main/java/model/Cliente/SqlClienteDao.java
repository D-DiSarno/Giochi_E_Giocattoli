package model.Cliente;

import controller.componenti.Paginator;
import model.Categoria.Categoria;
import model.ConPool.ConPool;
import model.Ordine.Ordine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlClienteDao implements ClienteDao<SQLException> {


    //SELECT * FROM %s LIMIT ?,?;
    public List<Cliente> fetchAccounts(Paginator paginator) throws SQLException {

        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM Cliente LIMIT ?,?")) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimite());
                System.out.println(paginator.getLimite()+" "+ paginator.getOffset());
                ResultSet rs = ps.executeQuery();
                List<Cliente> clienti = new ArrayList<>();
                while (rs.next()) {
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

                return clienti;
            }
        }
    }



        public Optional<Cliente> fetchAccountById(int id) throws SQLException {
            try (Connection con = ConPool.getConnection()) {
                try (   PreparedStatement ps =
                        con.prepareStatement("SELECT * FROM Cliente WHERE idCliente=?")){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Cliente cliente =new Cliente();
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setEmail(rs.getString("mail"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCognome(rs.getString("cognome"));
                    cliente.setVia(rs.getString("via"));
                    cliente.setCitta(rs.getString("citta"));
                    cliente.setCodice_postale(rs.getString("codice_postale"));
                    cliente.setRuolo(rs.getBoolean("ruolo"));


                }

                return Optional.ofNullable(cliente);
            }
        }
     }
        public Optional<Cliente> fetchAccountByMail(String mail) throws SQLException {
            try (Connection con = ConPool.getConnection()) {
                try (  PreparedStatement ps =
                        con.prepareStatement("SELECT * FROM Cliente WHERE mail=?")) {
                    ps.setString(1, mail);
                    ResultSet rs = ps.executeQuery();
                    Cliente cliente = new Cliente();
                    if (rs.next()) {

                        //  Cliente c=new ClienteExtractor().extraxt(set);
                        cliente.setIdCliente(rs.getInt("idCliente"));
                        cliente.setEmail(rs.getString("mail"));
                        cliente.setNome(rs.getString("nome"));
                        cliente.setCognome(rs.getString("cognome"));
                        cliente.setVia(rs.getString("via"));
                        cliente.setCitta(rs.getString("citta"));
                        cliente.setCodice_postale(rs.getString("codice_postale"));
                        cliente.setRuolo(rs.getBoolean("ruolo"));


                    }

                    return Optional.ofNullable(cliente);
                }
            }
        }
        public boolean createAccount (Cliente cliente)throws SQLException {
                 try (Connection con = ConPool.getConnection()) {
                      try( PreparedStatement ps =con.prepareStatement(
                                  "INSERT INTO Cliente (mail,nome,cognome,via,citta,codice_postale,ruolo,passwrd) VALUES(?,?,?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS)){
                        System.out.println(cliente.getPassword()+" "+cliente.getEmail()+" "+cliente.getNome()+" "+cliente.getCodice_postale()+" "+cliente.getRuolo());
                         ps.setString(1, cliente.getEmail());
                         ps.setString(2, cliente.getNome());
                         ps.setString(3, cliente.getCognome());
                         ps.setString(4, cliente.getVia());
                         ps.setString(5, cliente.getCitta());
                         ps.setString(6, cliente.getCodice_postale());
                         ps.setBoolean(7, cliente.getRuolo());
                         ps.setString(8, cliente.getPassword());
                          System.out.println(" pre-eseguito");
                         int rows = ps.executeUpdate();
                         System.out.println(rows);
                         ResultSet rs = ps.getGeneratedKeys();
                         System.out.println("eseguito");
//non rs.getInt  //key sale
                          int id = rs.next() ? rs.getInt(1): -1;
                         System.out.println(id);

                         cliente.setIdCliente(id);
                         System.out.println(cliente.getIdCliente());

                         return rows == 1;
                     }
                 }
             }

            public boolean updateAccount (Cliente cliente)throws SQLException {
                try (Connection con = ConPool.getConnection()) {
                    try (PreparedStatement ps =
                                 con.prepareStatement("UPDATE  Cliente SET nome=?, cognome=?, via=?, citta=?, codice_postale=?, mail=? WHERE idCliente=?;")) {
                        ps.setString(1, cliente.getNome());
                        System.out.println(cliente.getNome()+" "+cliente.getCognome()+" "+cliente.getIdCliente()+" "+cliente.getVia()+"\n"+cliente.getEmail()+"\n"+cliente.getCodice_postale());
                        ps.setString(2, cliente.getCognome());
                        ps.setString(3, cliente.getVia());
                        ps.setString(4, cliente.getCitta());
                        ps.setString(5, cliente.getCodice_postale());
                        ps.setString(6, cliente.getEmail());
                        ps.setInt(7, cliente.getIdCliente());
                         System.out.println(ps.toString());
                        int rows = ps.executeUpdate();
                        System.out.println("update");
                        return rows == 1;
                    }
                }

            }

            public boolean deleteAccountByMail (String mail) throws SQLException {

                try (Connection con = ConPool.getConnection()) {
                    try (PreparedStatement ps =
                                 con.prepareStatement("DELETE FROM Cliente WHERE mail=?;")) {
                        ps.setString(1, mail);
                        int rows = ps.executeUpdate();
                        return rows == 1;
                    }
                }
            }
        public boolean deleteAccountById (int id) throws SQLException {

            try (Connection con = ConPool.getConnection()) {
               try (PreparedStatement ps =
                           con.prepareStatement("DELETE FROM Cliente WHERE idCliente=?;")) {

                   ps.setInt(1, id);
                   System.out.println(ps.toString());
                   int rows = ps.executeUpdate();
                   System.out.println(rows);
                   return rows == 1;
               }
            }

        }

       public Optional<Cliente> fetchClientsWithCategory(int idCliente) throws SQLException{
            try (Connection con = ConPool.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement("Select * from categoria as cat INNER JOIN Preferenza as pref on pref.idCategoria =cat.idCategoria  INNER JOIN cliente cl on cl.idCliente = pref.idCliente where cl.idCliente=?")) {
                    ps.setInt(1, idCliente);
                    System.out.println(ps.toString());
                    ResultSet rs = ps.executeQuery();
                    List<Categoria> categoria = new ArrayList<>();
                    Optional<Cliente> cliente;
                    cliente=fetchAccountById(idCliente);
                   while (rs.next()) {
                        Categoria cat = new Categoria();
                        cat.setIdCategoria(rs.getString("idCategoria"));
                        cat.setTipologia(rs.getString("tipologia"));
                        cat.setEtaMinima(rs.getInt("etaMin"));
                        categoria.add(cat);

                    }
                    cliente.get().setPreferiti(categoria);
                   // System.out.println(cliente.get().getNome()+" "+cliente.get().getPreferiti().get(0).getTipologia());
                    return Optional.ofNullable(cliente.get());
                }
            }

        }
        //quantita ?
        public Optional<Cliente> fetchClientsWithOrders(int idCliente) throws SQLException{
            try (Connection con = ConPool.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement("Select * from ordine as ord where ord.idCliente=?")) {
                    ps.setInt(1, idCliente);
                    ResultSet rs = ps.executeQuery();
                    List<Ordine> ordini = new ArrayList<>();
                    Optional<Cliente> cliente;
                    cliente=fetchAccountById(idCliente);
                    while (rs.next()) {
                        Ordine ord = new Ordine();
                        ord.setNumeroOrdine(rs.getInt("numero_Ordine"));
                        ord.setTotale(rs.getDouble("totale"));
                        Date dataR = (rs.getDate("dataAcquisto"));
                        ord.setDataOrdine(dataR.toLocalDate());
                        ord.setVia(rs.getString("via"));
                        ord.setCitta(rs.getString("citta"));
                        ord.setCodice_postale(rs.getString("codice_postale"));
                        ord.setStato_ordine(rs.getString("stato_ordine"));
                        ord.setCliente(cliente.get());
                        ordini.add(ord);
                    }
                    cliente.get().setOrdini(ordini);
                 //System.out.println(cliente.get().getNome()+" "+cliente.get().getOrdini().get(0).getDataOrdine());
                    return Optional.ofNullable(cliente.get());
                }
            }
        }

    public Optional<Cliente> findAccount(String email, String passw, Boolean val)   throws SQLException{

        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("Select * from Cliente where passwrd=? AND mail=? AND ruolo=?;")) {
                ps.setString(1, passw);
                ps.setString(2, email);
                ps.setBoolean(3, val);
                System.out.println("entro da findAccount");
                System.out.println("PRE ESEGUO FIND");
                ResultSet rs = ps.executeQuery();
                Cliente cliente = new Cliente();
                System.out.println("POST ESEGUO FIND"+cliente.getNome()+" "+ps.toString());
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setEmail(rs.getString("mail"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCognome(rs.getString("cognome"));
                    cliente.setVia(rs.getString("via"));
                    cliente.setCitta(rs.getString("citta"));
                    cliente.setCodice_postale(rs.getString("codice_postale"));
                    cliente.setEmail(email);
                    cliente.setRuolo(val);
                }

                return Optional.ofNullable(cliente);
            }
        }
    }

     public int countAll() throws SQLException {
           try (Connection con = ConPool.getConnection()) {
               try (PreparedStatement ps = con.prepareStatement("Select count(*) as totaleClienti from Cliente")){
                       ResultSet set=ps.executeQuery();
                       int size=0;
                   if (set.next()) {
                       size = set.getInt("totaleClienti");
                   }
                   return size;
           }
       }
       }
     public boolean addPreferiti(int idCliente,String idCat) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO Preferenza (idCliente, idCategoria) VALUE (?,?);")){
               ps.setInt(1,idCliente);
               ps.setString(2,idCat);
System.out.println(ps.toString());
                int rows = ps.executeUpdate();
                return rows ==1;
            }
        }

     }


    }

