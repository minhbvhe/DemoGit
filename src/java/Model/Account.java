/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HOAN DO
 */
public class Account {
    private int accountId;
    private String username;
    private String password;
    private AccountRole roleid;

    public Account() {
    }

    public Account(int accountId, String username, String password, AccountRole roleid) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.roleid = roleid;
    }

    public Account(String username, String password, AccountRole roleid) {
        this.username = username;
        this.password = password;
        this.roleid = roleid;
    }

    public Account(int accountId) {
        this.accountId = accountId;
    }
    
    

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountRole getRoleid() {
        return roleid;
    }

    public void setRoleid(AccountRole roleid) {
        this.roleid = roleid;
    }
    
    
}
