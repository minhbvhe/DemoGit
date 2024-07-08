/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author HOAN DO
 */
public class Information {
    private Account id;
    private String name;
    private String img;
    private String address;
    private Date dob;
    private String gender;
    private String phone;
    private AccountRole roleid;

    public Information() {
    }

    public Information(Account id, String name, String img, String address, Date dob, String gender, String phone, AccountRole roleid) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.roleid = roleid;
    }

    
    
    public Account getId() {
        return id;
    }

    public void setId(Account id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountRole getRoleid() {
        return roleid;
    }

    public void setRoleid(AccountRole roleid) {
        this.roleid = roleid;
    }
    
    
}
