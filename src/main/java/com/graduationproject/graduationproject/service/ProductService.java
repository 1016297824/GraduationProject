package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Product;
import com.graduationproject.graduationproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 查询所有产品信息
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    // 通过类型查询产品信息
    public List<Product> findByProductType(String productType) {

        return productRepository.findByProductType(productType);
    }

    // 添加农产品信息
    public void save(Product product) {

        productRepository.save(product);
    }

    // 删除农产品信息
    public void deleteProduct(Product product) {

        productRepository.delete(product);
    }

    // 通过农产品名查找农产品
    public Product findByName(String name) {

        return productRepository.findByName(name);
    }

    // 初始化产品表
    public void initProduct() {

        List<Product> productList = new ArrayList<Product>();

        Product product = new Product("猪", "头", 20, 5, Product.productType1);
        Product product1 = new Product("鸡", "只", 50, 10, Product.productType1);
        Product product2 = new Product("鸭子", "只", 50, 10, Product.productType1);
        Product product3 = new Product("鹅", "只", 50, 10, Product.productType1);
        Product product4 = new Product("兔子", "只", 30, 5, Product.productType1);
        Product product5 = new Product("鹌鹑", "只", 50, 10, Product.productType1);

        Product product6 = new Product("鲫鱼", "斤", 100, 20, Product.productType2);
        Product product7 = new Product("鲤鱼", "斤", 100, 20, Product.productType2);
        Product product8 = new Product("白鲢鱼", "斤", 100, 20, Product.productType2);

        Product product9 = new Product("苹果", "斤", 100, 20, Product.productType3);
        Product product10 = new Product("枣", "斤", 100, 20, Product.productType3);
        Product product11 = new Product("石榴", "斤", 100, 20, Product.productType3);
        Product product12 = new Product("梨", "斤", 100, 20, Product.productType3);
        Product product13 = new Product("白菜", "斤", 100, 20, Product.productType3);
        Product product14 = new Product("土豆", "斤", 100, 20, Product.productType3);

        productList.add(product);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        productList.add(product7);
        productList.add(product8);
        productList.add(product9);
        productList.add(product10);
        productList.add(product11);
        productList.add(product12);
        productList.add(product13);
        productList.add(product14);

        productRepository.saveAll(productList);
    }
}
