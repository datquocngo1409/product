package com.code.product.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String product_code;

    @NotEmpty
    private String productname;

    @Min(0)
    private double price;

    @NotEmpty
    private String company;

    @NotEmpty
    private String producted;

    @NotEmpty
    private String description;

    @Min(0)
    private int discount;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    public Product() {
    }

    public Product(@NotEmpty String product_code, @NotEmpty String product_name, @Min(0) double price, @NotEmpty String company, @NotEmpty String producted, @NotEmpty String description, @Min(0) int discount, Category category) {
        this.product_code = product_code;
        this.productname = product_name;
        this.price = price;
        this.company = company;
        this.producted = producted;
        this.description = description;
        this.discount = discount;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProducted() {
        return producted;
    }

    public void setProducted(String producted) {
        this.producted = producted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
