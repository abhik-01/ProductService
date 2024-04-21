package dev.abhik.productservice.services;

import dev.abhik.productservice.dtos.FakestoreCategoryDto;
import dev.abhik.productservice.models.Product;

import java.util.List;

public interface ProductService {

    public Product getSingleProduct(Long id);
    public Product createProduct(String title, String image, String description, String category, double price);
    public List<Product> getAllProducts();
    public List<FakestoreCategoryDto> getCategories();
    public Product updateProduct(Long id, String title, String image, String description, String category, double price);
    public Product deleteProduct(Long id);
    public List<Product> getProductsByCategory(String category);
}
