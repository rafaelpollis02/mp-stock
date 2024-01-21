package br.com.resource;

import br.com.domain.Stock;
import br.com.exception.BusinessException;
import br.com.service.StockService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("api/v1/stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockResource {

    @Inject
    StockService stockService;

    @GET
    public List<Stock> getAllStock() {
        return stockService.getAllStock();
    }

    @GET
    @Path("/{idProduct}")
    public Stock getProductById(@PathParam("idProduct") Long idProduct) throws BusinessException {
        return stockService.getProductById(idProduct);
    }

    @POST
    @Transactional
    public Stock saveProductStock(Stock stock) {
        stockService.saveProductStock(stock);
        System.out.println("Criado " + stock.getQtdStock() + " itens no estoque para o Produto ID " + stock.getIdProduct());
        return stock;
    }

    @PUT
    @Path("/{idProduct}")
    @Transactional
    public void updateProductStock(@PathParam("idProduct") Long idProduct, @QueryParam("qtdStock") int qtdStock) throws BusinessException {
        stockService.updateProductStock(idProduct, qtdStock);
    }



}
