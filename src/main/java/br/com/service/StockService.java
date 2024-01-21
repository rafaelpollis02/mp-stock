package br.com.service;

import br.com.domain.Stock;
import br.com.exception.BusinessException;
import br.com.repository.StockRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;

import java.util.List;

@ApplicationScoped
public class StockService {

    @Inject
    StockRepository stockRepository;

    public List<Stock> getAllStock() {
        return stockRepository.listAll();
    }

    public Stock getProductById(@PathParam("idProduct") Long idProduct) throws BusinessException {
        Stock stock = stockRepository.findByIdProduct(idProduct);

        if (stock == null) {
            throw new BusinessException("Não existe um product com o ID " + idProduct);
        } else {
            System.out.println("Produto (" + stock.getIdProduct() + ") consultado com quantidade em estoque de " + stock.getQtdStock() + " itens !");
            return stock;
        }
    }

    public Stock saveProductStock(Stock stock) {
        stockRepository.persist(stock);
        return stock;
    }

    public void updateProductStock(Long idProduct, int qtdStock) throws BusinessException {
        Stock stock = stockRepository.findByIdProduct(idProduct);

        if (stock == null) {
            throw new BusinessException("Não existe um produto com o ID " + idProduct);
        }

        stock.setQtdStock(qtdStock);
        stockRepository.persist(stock);


        System.out.println("Atualizado o estoque do Produto ID " + stock.getIdProduct() + " para " + stock.getQtdStock() + " itens!");
     }


}
