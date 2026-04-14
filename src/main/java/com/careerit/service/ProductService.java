package com.careerit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.careerit.entity.Product;
import com.careerit.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private S3Service s3Service;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#category")
    public List<Product> getByCategory(String category) {
        System.out.println("Fetching from DB...");
        return productRepository.findByCategory(category);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product addProductWithImage(Product product, MultipartFile file) throws Exception {
        String imageUrl = s3Service.uploadFile(file);
        product.setPimage(imageUrl);
        return productRepository.save(product);
    }
}