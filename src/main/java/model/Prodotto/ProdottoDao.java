package model.Prodotto;

import controller.Http.Condition;
import controller.componenti.Paginator;
import model.Cliente.Cliente;

import java.util.List;
import java.util.Optional;

public interface ProdottoDao <E extends Exception>{
    List<Prodotto> fetchProducts(Paginator paginator) throws E;
    Optional<Prodotto> fetchProduct(int id)throws E;
    List<Prodotto> fetchProductsLimit(int limite) throws E;
    boolean createProduct(Prodotto prodotto)throws E;
    boolean updateProduct(Prodotto prodotto)throws E;
    boolean deleteProduct(int id) throws E;
    int countAll() throws E;
    List<Prodotto>search(List<Condition> conditions) throws E;
    int getTotale()throws E;
    Optional<Prodotto> findProdotto(String ricerca) throws E;
    List<Prodotto>searchEta(int min,int max) throws E;
    Optional<Prodotto> fetchProductCategoriaProduttore(int idProdotto) throws E;
}

