package model.Cliente;


import controller.componenti.Paginator;
import model.Categoria.Categoria;
import model.Ordine.Ordine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ClienteDao<E extends Exception>{
    List<Cliente> fetchAccounts(Paginator paginator) throws E;
    Optional<Cliente> fetchAccountById(int id)throws E;
    Optional<Cliente> fetchAccountByMail(String email) throws E;
    boolean createAccount(Cliente cliente)throws E;
    boolean updateAccount(Cliente cliente)throws E;
    boolean deleteAccountById(int id) throws E;
    boolean deleteAccountByMail(String email) throws E;
    Optional<Cliente> fetchClientsWithCategory(int idCliente) throws E;
    Optional<Cliente>  fetchClientsWithOrders(int idCliente) throws E;
    Optional<Cliente> findAccount(String email,String passw,Boolean val) throws E;
    int countAll() throws E;
    boolean  addPreferiti(int idCliente,String idCat) throws E;
}
