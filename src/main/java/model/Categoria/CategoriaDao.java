package model.Categoria;

import controller.componenti.Paginator;

import java.util.List;
import java.util.Optional;

public interface CategoriaDao <E extends Exception>{
        Categoria fetchCategory(String idCategoria) throws E;
        Optional<Categoria> fetchCategoryAge(int etaMinima) throws E;
        List<Categoria> fetchCategories(Paginator paginator) throws E;
        List<Categoria> fetchCategoriesAll() throws E;
        Optional<Categoria> fetchCategories(String id) throws E;
        boolean createCategory(Categoria categoria)throws E;
        boolean updateCategory(Categoria categoria)throws E;
        boolean deleteCategory(String idCategoria) throws E;
        Optional<Categoria> fetchCategoryWithProducts(String idCategoria) throws E;
        Optional<Categoria> fetchCategoryWithClients(String idCategoria) throws E;
        int countAll() throws E;
}
