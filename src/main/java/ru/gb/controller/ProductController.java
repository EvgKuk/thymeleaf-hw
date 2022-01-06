package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/ GET
    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("products",productService.getAll());
        return "product_list";
    }

    // http://localhost:8080/app/info/3 GET
    @GetMapping("/info/{id}")
    public String getProductInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_info";
    }

    // http://localhost:8080/app/add GET
    @GetMapping("/add")
    public String getProductAddFrom() {
        return "product_form";
    }

    // http://localhost:8080/app/add POST
    @PostMapping("/add")
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/";
    }

    // http://localhost:8080/app/delete/3 POST
    @PostMapping("/delete/{id}")
    public String saveProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }
}
