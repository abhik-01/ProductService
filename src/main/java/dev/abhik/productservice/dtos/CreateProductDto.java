package dev.abhik.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;
}
