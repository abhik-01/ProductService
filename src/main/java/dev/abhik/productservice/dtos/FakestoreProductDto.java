package dev.abhik.productservice.dtos;

import dev.abhik.productservice.models.Category;
import dev.abhik.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakestoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public Product toProduct() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setImage(getImage());
        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);
        return product;
    }
}

