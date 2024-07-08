/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.DAO;
import Model.Item;
import Model.Order;

import Model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOAN DO
 */
public class CartServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        DAO dao = DAO.INSTANCE;
        int quantity = 1;
        int id;
        double total = 0;
        int totalQuantity = 0;
        try {
            if (id_raw != null) {
                id = Integer.parseInt(id_raw);
            Product p = dao.getProductByID(id);
            if (p != null) {
                if (request.getParameter("quantity") !=null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                HttpSession session = request.getSession();
                if (session.getAttribute("order") == null) {
                    Order order = new Order();
                    List<Item> listItem = new ArrayList<>();
                    Item item = new Item();
                    item.setId(id);
                    item.setQuantity(quantity);
                    item.setProduct(p);
                    item.setPrice(p.getPrice());
                    listItem.add(item);
                    order.setItem(listItem);
                    total += item.getPrice() * item.getQuantity();
                    totalQuantity += item.getQuantity();
                    session.setAttribute("order", order);
                    session.setAttribute("total", total);
                    session.setAttribute("totalQuantity", totalQuantity);
                }
                else{
                    Order order = (Order) session.getAttribute("order");
                    List<Item> listItem = order.getItem();
                    boolean check = false;
                    for (Item item : listItem) {
                        if (item.getProduct().getId_product() == p.getId_product()) {
                            item.setQuantity(item.getQuantity() + quantity);
                            check = true;
                        }
                        total += item.getPrice() * item.getQuantity();
                        totalQuantity += item.getQuantity();
                    }
                    if (check == false) {
                    Item item = new Item();
                    item.setId(id);
                    item.setQuantity(quantity);
                    item.setProduct(p);
                    item.setPrice(p.getPrice());
                    listItem.add(item);
                    total += item.getPrice() * item.getQuantity();
                    totalQuantity += item.getQuantity();
                    }
                    session.setAttribute("order", order);
                    session.setAttribute("total", total);
                    session.setAttribute("totalQuantity", totalQuantity);
                }
               
            }
            
            response.sendRedirect("addtocart");
            }
            else{
            response.sendRedirect("addtocart");
            }           
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
