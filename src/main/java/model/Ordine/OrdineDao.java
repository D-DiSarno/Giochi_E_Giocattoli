package model.Ordine;

import controller.componenti.Paginator;

import java.util.List;
import java.util.Optional;

public interface OrdineDao <E extends Exception>{
    List<Ordine> fetchOrders(Paginator paginator) throws E;
    Optional<Ordine> fetchOrdiniByNumero(int numero_Ordine) throws E;
    boolean createOrder ( Ordine ordine) throws E;
    boolean updateOrder (Ordine ordine) throws E;
    boolean updateOrderState(Ordine ordine)throws E;
    boolean deleteOrder (int numeroOrdine) throws E;
    Optional<Ordine> fetchOrdersWithProducts(int numero_Ordine)throws  E;
    Optional<Ordine> fetchOrdiniByStato(String stato) throws E;
    int countAll() throws E;
    double getTotaleIncasso()throws E;
    List<Ordine> fetchOrdersWithProductsList(int numero_ordine) throws E;
}
