/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HOAN DO
 */
public class Seller {
    private Product sell_Id;
    private Account id;

    public Seller() {
    }

    public Seller(Product sell_Id, Account id) {
        this.sell_Id = sell_Id;
        this.id = id;
    }

    public Product getSell_Id() {
        return sell_Id;
    }

    public void setSell_Id(Product sell_Id) {
        this.sell_Id = sell_Id;
    }

    public Account getId() {
        return id;
    }

    public void setId(Account id) {
        this.id = id;
    }

    
}
