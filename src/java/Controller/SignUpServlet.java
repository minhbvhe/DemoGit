/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.DAO;
import Model.Account;
import Model.AccountRole;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HOAN DO
 */
public class SignUpServlet extends HttpServlet {
   private String ZIPCODE = "HE03008923";
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
            out.println("<title>Servlet SignUpServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("/Views/Login.jsp").forward(request, response);
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
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String role_raw = request.getParameter("roleid");
        String zipcode = request.getParameter("zipcode");
        int roleid;
        DAO dao = DAO.INSTANCE;
        try {
            roleid = Integer.parseInt(role_raw);
            if (!password.equals(repass)) {
                request.setAttribute("mss", "Repass not match with password!!!");
                request.getRequestDispatcher("/Views/Login.jsp").forward(request, response);
            }
            else if (!zipcode.equals(ZIPCODE)) {
                request.setAttribute("mss", "Zipcode not match database!!!");
                request.getRequestDispatcher("/Views/Login.jsp").forward(request, response);
            }
            else{
                Account acc = dao.checkUser(username, password);
            if (acc == null) {
                
            acc = new Account(username, password, new AccountRole(roleid, "NhanVien"));
            dao.createAccount(acc);
            request.setAttribute("mss", "Create account succesfully!!!");
            request.getRequestDispatcher("/Views/Login.jsp").forward(request, response);
            }
            else{
            request.setAttribute("mss", "Account always exited!!!");
            request.getRequestDispatcher("/Views/Login.jsp").forward(request, response);
            }
            }   
        } catch (IOException | NumberFormatException e) {
            e.getStackTrace();
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
