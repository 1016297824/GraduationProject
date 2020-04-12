package com.graduationproject.graduationproject.repository;

import com.graduationproject.graduationproject.entity.Product;
import com.graduationproject.graduationproject.repository.impl.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CustomizedRepository<Product, Integer> {

    @Query("from Product")
    List<Product> findAll();

    @Query("select p from Product p where p.productType=:productType")
    List<Product> findByProductType(@Param("productType") String productType);

    @Query("select p from Product p where p.name=:name")
    Product findByName(@Param("name") String name);
}
