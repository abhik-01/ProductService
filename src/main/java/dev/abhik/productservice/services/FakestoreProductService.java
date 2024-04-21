package dev.abhik.productservice.services;
import dev.abhik.productservice.dtos.FakestoreCategoryDto;
import dev.abhik.productservice.dtos.FakestoreProductDto;
import dev.abhik.productservice.dtos.UpdateProductDto;
import dev.abhik.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;


@Service
public class FakestoreProductService implements ProductService{
        private final RestTemplate restTemplate;

        public FakestoreProductService(RestTemplate restTemplate){
                this.restTemplate = restTemplate;
        }

        @Override
        public Product getSingleProduct(Long id) {
                FakestoreProductDto fakestoreProductDto = restTemplate.getForObject(
                        "https://fakestoreapi.com/products/"+id, FakestoreProductDto.class);
            return fakestoreProductDto.toProduct();
        }

        @Override
        public Product createProduct(String title, String image, String description, String category, double price){
                FakestoreProductDto fakestoreProductDto = new FakestoreProductDto();
                fakestoreProductDto.setTitle(title);
                fakestoreProductDto.setImage(image);
                fakestoreProductDto.setDescription(description);
                fakestoreProductDto.setCategory(category);
                fakestoreProductDto.setPrice(price);
                FakestoreProductDto response = restTemplate.postForObject(
                        "https://fakestoreapi.com/products", fakestoreProductDto, FakestoreProductDto.class);
                return response.toProduct();
        }

        @Override
        public List<Product> getAllProducts() {
                FakestoreProductDto[] fakestoreProductDtos = restTemplate.getForObject(
                        "https://fakestoreapi.com/products", FakestoreProductDto[].class);
                List<Product> products = new ArrayList<>();
                for (FakestoreProductDto fakestoreProductDto : fakestoreProductDtos) {
                        products.add(fakestoreProductDto.toProduct());
                }
                return products;
        }

        @Override
        public List<FakestoreCategoryDto> getCategories() {
                String[] response = restTemplate.getForObject(
                        "https://fakestoreapi.com/products/categories", String[].class);
                List<FakestoreCategoryDto> fakestoreCategoryDtos = new ArrayList<>();
                for (String category : response) {
                        FakestoreCategoryDto fakestoreCategoryDto = new FakestoreCategoryDto();
                        fakestoreCategoryDto.setTitle(category);
                        fakestoreCategoryDtos.add(fakestoreCategoryDto);
                }
                return fakestoreCategoryDtos;

        }

        @Override
        public Product updateProduct(Long id, String title, String image, String description, String category, double price) {
                UpdateProductDto updateProductDto = new UpdateProductDto();
                updateProductDto.setId(id);
                updateProductDto.setTitle(title);
                updateProductDto.setImage(image);
                updateProductDto.setDescription(description);
                updateProductDto.setCategory(category);
                updateProductDto.setPrice(price);
                HttpEntity<UpdateProductDto> requestCallback = new HttpEntity<>(updateProductDto);
                ResponseEntity<FakestoreProductDto> response = restTemplate.exchange(
                        "https://fakestoreapi.com/products/"+id, HttpMethod.PUT,
                        requestCallback, FakestoreProductDto.class, id);
                return response.getBody().toProduct();
        }

        @Override
        public Product deleteProduct(Long id) {
                ResponseEntity<FakestoreProductDto> fakestoreProductDto = restTemplate.exchange(
                        "https://fakestoreapi.com/products/"+id, HttpMethod.DELETE,
                        null, FakestoreProductDto.class, id);
                return fakestoreProductDto.getBody().toProduct();
        }

        @Override
        public List<Product> getProductsByCategory(String category) {
                FakestoreProductDto[] fakestoreProductDtos = restTemplate.getForObject(
                        "https://fakestoreapi.com/products/category/"+category, FakestoreProductDto[].class);
                List<Product> products = new ArrayList<>();
                for (FakestoreProductDto fakestoreProductDto : fakestoreProductDtos) {
                        products.add(fakestoreProductDto.toProduct());
                }
                return products;
        }
}
