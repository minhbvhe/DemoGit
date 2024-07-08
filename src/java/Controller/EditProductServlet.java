/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.DAO;
import Model.Account;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author HOAN DO
 */
public class EditProductServlet extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProductServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProductServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        DAO dao = DAO.INSTANCE;
        String pid_raw = request.getParameter("pid");
        List<Category> listC = dao.listCategory();
        int pid;
        try {
            pid = Integer.parseInt(pid_raw);
            Product product = dao.getAllProductByID(pid);
            if (product != null) {
                request.setAttribute("listC", listC);
                request.setAttribute("detail", product);
                request.getRequestDispatcher("/Views/Edit.jsp").forward(request, response);
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
        String pid_raw = request.getParameter("id");
       String name = request.getParameter("name");
       String img = request.getParameter("image");
       String price_raw = request.getParameter("price");
       String title = request.getParameter("title");
       String description = request.getParameter("description");
       String pcategory_raw = request.getParameter("category");
       DAO dao = DAO.INSTANCE;
       int pcategory;
       double price;
       int pid;
        try {
            pcategory = Integer.parseInt(pcategory_raw);
            pid = Integer.parseInt(pid_raw);
            price = Double.parseDouble(price_raw);
            dao.editProduct(name, img, price, title, description, pcategory, pid);
            response.sendRedirect("manager");
        } catch (NumberFormatException e) {
        }
 
         
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
