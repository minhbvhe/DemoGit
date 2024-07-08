/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Account;
import Model.AccountRole;
import Model.Category;
import Model.Information;
import Model.Product;
import Model.Seller;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HOAN DO
 */
public class DAO {

    public static DAO INSTANCE = new DAO();
    private Connection connect;
    private String status = "OK";

 

    public List<Product> listProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId_product(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImg(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return list;
    }
    
    public List<Category> listCategory(){
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category cate = new Category();
                cate.setCateID(rs.getInt("cateID"));
                cate.setCateName(rs.getString("cname"));
                list.add(cate);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return list;
    }
    
    public Product getLastProduct(){
        String sql = "select top 1 * from Product\n"
                + "order by id desc";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getString("name"), 
                        rs.getString("image"), 
                        rs.getDouble("price"), 
                        rs.getString("title"), 
                        rs.getString("description"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }

    public List<Product> getProductByCateID(int cid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product\n"
                + "where cateID = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId_product(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImg(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
    
    public Product getProductByID(int pid) {
        Product product = new Product();
        String sql = "select * from Product\n"
                + "where id = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {               
                product.setId_product(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImg(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                 return product;
            }         
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
    
    public List<Product> getProductByTitle(String text) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product\n"
                + "where title like ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId_product(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImg(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
    
    public List<Product> getProductBySellID(int pid) {
        List<Product> list = new ArrayList<>();
        String sql = "select p.id, p.name, p.image, p.price, p.title, p.description  \n"
                + "from Product p join Seller s on p.id = s.sell_ID \n"
                + "join Account a on s.id = a.id where a.id = ?";
        
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId_product(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImg(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
    
    public Account checkUser(String name, String pass){
        String sql = "select a.id, a.username, a.password, ar.roleid, ar.rolename "
                + "from Account a join AccountRole ar on a.roleid = ar.roleid\n" 
                + "where username = ? and password = ?";
        try {
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Account acc = new Account();
                acc.setAccountId(rs.getInt("id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setRoleid(new AccountRole(rs.getInt("roleid"), rs.getString("rolename")));
                return acc;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
    
    public void createAccount(Account acc) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[roleid])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, acc.getUsername());
            st.setString(2, acc.getPassword());
            st.setInt(3, acc.getRoleid().getRoleid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void deleteProductByID(int pid){
        String sql = "delete from Product where id=?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    
    public void deleteProductBySellID(int pid){
        String sql = "delete from Product where id=?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    
    
    public Account getAccountByUser(String name){
        String sql = "select a.id, a.username, a.password, ar.roleid, ar.rolename "
                + "from Account a join AccountRole ar on a.roleid = ar.roleid\n" 
                + "where username = ?";
        try {
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Account acc = new Account();
                acc.setAccountId(rs.getInt("id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setRoleid(new AccountRole(rs.getInt("roleid"), rs.getString("rolename")));
                return acc;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
    
    
    public void insertSeller(int sellID, int idAccount){
        String sql = "insert into Seller (sell_ID,id)"
                + "values(?,?)";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, sellID);
            st.setInt(2, idAccount);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    public void insertProduct(String name, String img, Double price, String title, String description, int cateID){
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([name]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[title]\n"
                + "           ,[description]\n"
                + "           ,[cateID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, img);
            st.setDouble(3, price);
            st.setString(4, title);
            st.setString(5, description);
            st.setInt(6, cateID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }

    }
    
    public Product getAllProductByID(int pid) {
        String sql = "select p.id, p.name, p.image, p.price, p.title, p.description, c.cateID, c.cname  \n"
                + "from Product p join Category c on p.cateID = c.cateID where p.id = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId_product(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImg(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setCateID(new Category(rs.getInt("cateID"), rs.getString("cname")));
                return product;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
    
//    public List<Account> getAccount() {
//        List<Account> list = new ArrayList<>();
//        String sql = "select * from Account";
//        try {
//            PreparedStatement ps = connect.prepareStatement(sql);;
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Account acc = new Account();
//                acc.setAccountId(rs.getInt("id"));
//                acc.setUsername(rs.getString("username"));
//                acc.setPassword(rs.getString("password"));
//                
//            }
//            return list;
//        } catch (SQLException e) {
//            e.getStackTrace();
//        }
//        return null;
//    }
    
    public void editProduct(String name, String img, Double price, String title, String description, int cateID, int pid){
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [name] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[cateID] = ?\n"
                + " WHERE id =?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, img);
            st.setDouble(3, price);
            st.setString(4, title);
            st.setString(5, description);
            st.setInt(6, cateID);
            st.setInt(7, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    
    public int countProduct(){
        String sql = "select count(*) from Product ";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return 0;
    }
    
    public List<Product> pagingProduct(int index){
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product \n"
                + "order by id\n"
                + "offset ? rows fetch next 6 rows only;";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, (index-1)*6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                Product product = new Product();
                product.setId_product(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImg(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                list.add(product);
            }
        } catch (SQLException e) {
        }
        return list;
    }
     public List<Information> getInformation() {
         List<Information> list = new ArrayList<>();
         String sql = "select a.id, i.fullname, i.img, i.address, i.dob, i.gender, i.phone,ar.roleid, ar.rolename \n"
                 + "from Information i join AccountRole ar on i.roleid = ar.roleid join Account a on i.id = a.id";
         try {
             PreparedStatement st = connect.prepareStatement(sql);
             ResultSet rs = st.executeQuery();
             while (rs.next()) {                 
                 Information in4 = new Information();
                 in4.setId(new Account(rs.getInt("id")));
                 in4.setName(rs.getString("fullname"));
                 in4.setImg(rs.getString("img"));
                 in4.setAddress(rs.getString("address"));
                 in4.setDob(rs.getDate("dob"));
                 in4.setGender(rs.getString("gender"));
                 in4.setPhone(rs.getString("phone"));
                 in4.setRoleid(new AccountRole(rs.getInt("roleid"), 
                         rs.getString("rolename")));
                 list.add(in4);
             }
         } catch (SQLException e) {
             e.getStackTrace();
         }
         return list;
    }
    
     public Information getInformationbyID(int aid){
         String sql = "select a.id, i.fullname, i.img, i.address, i.dob, i.gender, i.phone,ar.roleid, ar.rolename \n"
                 + "from Information i join AccountRole ar on i.roleid = ar.roleid join Account a on i.id = a.id\n"
                 + "where a.id = ?";
          try {
             PreparedStatement st = connect.prepareStatement(sql);
             st.setInt(1, aid);
             ResultSet rs = st.executeQuery();
              while (rs.next()) {
                  Information in4 = new Information();
                 in4.setId(new Account(rs.getInt("id")));
                 in4.setName(rs.getString("fullname"));
                 in4.setImg(rs.getString("img"));
                 in4.setAddress(rs.getString("address"));
                 in4.setDob(rs.getDate("dob"));
                 in4.setGender(rs.getString("gender"));
                 in4.setPhone(rs.getString("phone"));
                 in4.setRoleid(new AccountRole(rs.getInt("roleid"), 
                         rs.getString("rolename")));
                 return in4;
              }
          }catch(SQLException e){
              e.getStackTrace();
          }
          return null;
     }
     
     public void editAccount(String name, String img, String address, String dob, String gender, String phone, int roleid, int pid){
        String sql = "UPDATE [dbo].[Information]\n"
                 + "   SET \n"
                 + "      [fullname] = ?\n"
                 + "      ,[img] = ?\n"
                 + "      ,[address] = ?\n"
                 + "      ,[dob] = ?\n"
                 + "      ,[gender] = ?\n"
                 + "      ,[phone] = ?\n"
                 + "      ,[roleid] = ?\n"
                 + " WHERE [id] = ?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, img);
            st.setString(3, address);
            st.setDate(4, java.sql.Date.valueOf(dob));
            st.setString(5, gender);
            st.setString(6, phone);
            st.setInt(7, roleid);
            st.setInt(8, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
     
     public void deleteInformationByID(int aid){
        String sql = "delete from Information where id=?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, aid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
     
     public Information getLastInformation(){
        String sql = "select top 1 a.id, i.fullname, i.img, i.address, i.dob, i.gender, i.phone,ar.roleid, ar.rolename\n"
                 + "       from Information i join AccountRole ar on i.roleid = ar.roleid join Account a on i.id = a.id\n"
                 + "order by id desc";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              return new Information(new Account(rs.getInt("id")), 
                        rs.getString("fullname"),
                        rs.getString("img"),
                        rs.getString("address"),
                        rs.getDate("dob"),
                        rs.getString("gender"),
                        rs.getString("phone"), 
                        new AccountRole(rs.getInt("roleid"), rs.getString("rolename")));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
     
    public void insertInformation(int id,String fullname, String img, String address, String dob, String gender, String phone, int roleid){
        String sql = "INSERT INTO [dbo].[Information]\n"
                 + "           ([id]\n"
                 + "           ,[fullname]\n"
                 + "           ,[img]\n"
                 + "           ,[address]\n"
                 + "           ,[dob]\n"
                 + "           ,[gender]\n"
                 + "           ,[phone]\n"
                 + "           ,[roleid])\n"
                 + "     VALUES\n"
                 + "           (?,?,?,?,?,?,?,?)";
            try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, fullname);
            st.setString(3, img);
            st.setString(4, address);
            st.setDate(5, java.sql.Date.valueOf(dob));
            st.setString(6, gender);
            st.setString(7, phone);
            st.setInt(8, roleid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
     
    
    public void insertUserInfo(String name, String address, String phone){
        String sql = "INSERT INTO [dbo].[UserInfo]\n"
                + "           ([name]\n"
                + "           ,[address]\n"
                + "           ,[phone])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
            try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, address);
            st.setString(3, phone);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    
    public void insertOrder(int id_user, int id_product, double total, boolean status){
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([id_user]\n"
                + "           ,[id_product]\n"
                + "           ,[total]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
            try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, id_user);
            st.setInt(2, id_product);
            st.setDouble(3, total);
            st.setBoolean(4, status);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    
    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        String sql = "select * from UserInfo";
        try {
            PreparedStatement st = connect.prepareStatement(sql); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        DAO dao = DAO.INSTANCE;
        List<User> list = dao.getAllUser();
       if (list== null) {
           System.out.println("Nothing else");
        }
        else{
            for (User user : list) {
                System.out.println(user.getId());
           }
        }         
    }
    }

