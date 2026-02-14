package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Product p = new Product();
        p.setName("Pizza");
        p.setCategory("Food");
        p.setQuantity(1);
        p.setDescription("Cheese Pizza");
        p.setActive(true);
        p.setPrice(10000);
        p.setSku("p1");

        session.save(p);

        t.commit();
        session.close();

        System.out.println("Product Created");

        session = factory.openSession();

        Product readProduct = session.get(Product.class, p.getId());
        System.out.println("Product Name: " + readProduct.getName());

        session.close();

        session = factory.openSession();
        t = session.beginTransaction();

        Product updateProduct = session.get(Product.class, p.getId());
        updateProduct.setPrice(12000);

        t.commit();
        session.close();

        System.out.println("Product Updated");

        session = factory.openSession();
        t = session.beginTransaction();

        Product deleteProduct = session.get(Product.class, p.getId());
        session.delete(deleteProduct);

        t.commit();
        session.close();

        System.out.println("Product Deleted");

        factory.close();
    }
}