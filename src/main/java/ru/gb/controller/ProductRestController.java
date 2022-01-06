package ru.gb.controller;

import org.springframework.web.bind.annotation.*;
import ru.gb.dto.ProductDto;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/rest/all GET
    @GetMapping("/all")
    public List<ProductDto> getAllProducts() {
        return productService.getAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    // http://localhost:8080/app/rest/info/3 GET
    @GetMapping("/info/{id}")
    public ProductDto getProductInfo(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    // http://localhost:8080/app/rest/add POST
    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    // http://localhost:8080/app/rest/del GET
    @GetMapping("/del/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
