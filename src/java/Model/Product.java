/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HOAN DO
 */
public class Product {
    private int id_product;
    private String name;
    private String img;
    private double price;
    private String title;
    private String description;
    private Category cateID;
    private int mount;

    public Product() {
    }

    public Product(int id_product, String name, String img, double price, String title, String description, Category cateID) {
        this.id_product = id_product;
        this.name = name;
        this.img = img;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
    }

    public Product(int id_product, String name, String img, double price, String title, String description) {
        this.id_product = id_product;
        this.name = name;
        this.img = img;
        this.price = price;
        this.title = title;
        this.description = description;
    }
    
    

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCateID() {
        return cateID;
    }

    public void setCateID(Category cateID) {
        this.cateID = cateID;
    } 

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }  
}
