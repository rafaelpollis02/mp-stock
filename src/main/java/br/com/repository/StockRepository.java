package br.com.repository;

import br.com.domain.Stock;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockRepository implements PanacheRepository<Stock> {

    public Stock findByIdProduct(Long idProduct) {
        return find("idProduct", idProduct).firstResult();
    }

}
