package com.example.stock;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class StockController {
    
    @Autowired
	private StockRepository stockRepository;
    
    @GetMapping(path = "/stock")
    @CrossOrigin()
    List<Stock> getAll(){
        List<Stock> stocks = new ArrayList<Stock>();
        for (Stock s : stockRepository.findAll()) {
            stocks.add(s);
		}
        return stocks;
    }

    @GetMapping(path = "/stock/{id}")
    @CrossOrigin()
    Stock getSingle(@PathVariable Integer id) {
        return stockRepository.findById(id).get();
    }

    @PostMapping(path = "/stock", consumes="application/json", produces = "application/json")
    @CrossOrigin()
    ResponseEntity<Object> add(@RequestBody Stock s) {
        
        stockRepository.save(s);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(s.getId())
            .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/stock/{id}", consumes="application/json", produces = "application/json")
    @CrossOrigin()
    Stock update(@PathVariable Integer id, @RequestBody Stock updatedStock) {

        Stock dbStock = stockRepository.findById(id).get();
        dbStock.setName(updatedStock.getName());
        dbStock.setPrice(updatedStock.getPrice());
        dbStock.setDividend(updatedStock.getDividend());
        
        stockRepository.save(dbStock);
        
        return dbStock;
    }

    @DeleteMapping(path = "/stock/{id}")
    @CrossOrigin()
    void delete(@PathVariable Integer id) {
        stockRepository.deleteById(id);
    }
}