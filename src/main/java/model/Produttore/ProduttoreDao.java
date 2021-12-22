package model.Produttore;



import controller.componenti.Paginator;
import model.Ordine.Ordine;

import java.util.List;
import java.util.Optional;

public interface ProduttoreDao <E extends Exception>{
    List<Produttore> fetchProducers(Paginator paginator) throws E;
    Optional<Produttore> fetchProducer(String id) throws E;
     List<Produttore> fetchProducersAll() throws E;
    boolean createProducer (Produttore produttore)throws E;
    boolean updateProduces (Produttore produttore,String id)throws E;
    Optional<Produttore> fetchProducersWithProducts(String id) throws E;
    boolean deleteProducer (String idProduttore)throws E;
    int countAll() throws E;
}