package com.example.rothurtech.orderservice.Repository;

import com.example.rothurtech.orderservice.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    // Finds product with exact name
    // SELECT * FROM products WHERE name = ?

    // Finds products with price less than value
    //List<Product> findByPriceLessThan(Double price);
    // SELECT * FROM products WHERE price < ?

    // Finds products with name containing string and price greater than value
    //List<Product> findByNameContainingAndPriceGreaterThan(String name, Double price);
    // SELECT * FROM products WHERE name LIKE %?% AND price > ?
}

//// When your application starts, Spring creates a proxy implementation of your interface
//public class ProductRepositoryProxy implements ProductRepository {
//    // Spring injects the EntityManager
//    @Autowired
//    private EntityManager entityManager;
//
//    // This is what actually happens behind the scenes for findByName
//    @Override
//    public Product findByName(String name) {
//        // Spring automatically generates JPQL query based on method name
//        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
//
//        return entityManager.createQuery(jpql, Product.class)
//                .setParameter("name", name)
//                .getSingleResult();
//    }
//}