package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");

        SessionFactory factory = conf.buildSessionFactory();
        Scanner sc = new Scanner(System.in);
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Product p = new Product();

        System.out.print("Enter Name: ");
        p.setName(sc.next());

        System.out.print("Enter Category: ");
        p.setCategory(sc.next());

        System.out.print("Enter Quantity: ");
        p.setQuantity(sc.nextInt());

        System.out.print("Enter Description: ");
        sc.nextLine();
        p.setDescription(sc.nextLine());

        System.out.print("Enter Price: ");
        p.setPrice(sc.nextDouble());

        System.out.print("Enter SKU: ");
        p.setSku(sc.next());

        p.setActive(true);

        session.save(p);
        t.commit();
        session.close();

        System.out.println("Product Created with ID: " + p.getId());

        session = factory.openSession();

        Product readProduct = session.get(Product.class, p.getId());
        System.out.println("\n--- Product Details ---");
        System.out.println("Name: " + readProduct.getName());
        System.out.println("Category: " + readProduct.getCategory());
        System.out.println("Quantity: " + readProduct.getQuantity());
        System.out.println("Description: " + readProduct.getDescription());
        System.out.println("Price: " + readProduct.getPrice());
        System.out.println("SKU: " + readProduct.getSku());

        session.close();

        // ================= UPDATE =================
        session = factory.openSession();
        t = session.beginTransaction();

        Product updateProduct = session.get(Product.class, p.getId());

        System.out.print("\nEnter New Price: ");
        double newPrice = sc.nextDouble();
        updateProduct.setPrice(newPrice);

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
        sc.close();
    }
}