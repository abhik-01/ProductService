package dev.abhik.productservice.controllers;

import dev.abhik.productservice.dtos.CreateProductDto;
import dev.abhik.productservice.dtos.FakestoreCategoryDto;
import dev.abhik.productservice.dtos.UpdateProductDto;
import dev.abhik.productservice.models.Product;
import dev.abhik.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody CreateProductDto createProductDto){
        return productService.createProduct(
                createProductDto.getTitle(),
                createProductDto.getImage(),
                createProductDto.getDescription(),
                createProductDto.getCategory(),
                createProductDto.getPrice()
        );
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products/categories")
    public List<FakestoreCategoryDto> getCategories(){
        return productService.getCategories();
    }

    @PutMapping("/product/{id}")
    public Product updateProductById(@PathVariable("id") Long id, @RequestBody UpdateProductDto updateProductDto){
        return productService.updateProduct(
                id,
                updateProductDto.getTitle(),
                updateProductDto.getImage(),
                updateProductDto.getDescription(),
                updateProductDto.getCategory(),
                updateProductDto.getPrice());
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }


    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return productService.getProductsByCategory(category);
    }

}
