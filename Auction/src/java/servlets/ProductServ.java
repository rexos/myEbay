/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DbManager;
import database.Product;
import database.Purchased;
import database.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alex
 */
public class ProductServ extends HttpServlet {

    private Product prod;
    private DbManager manager;
    private HttpSession session;

    @Override
    public void init() {
        manager = (DbManager) super.getServletContext().getAttribute("dbmanager");
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        session = request.getSession(true);
        
        
        try {
            check();
        } catch (SQLException ex) {
            Logger.getLogger(ProductServ.class.getName()).log(Level.SEVERE, null, ex);
        }

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            prod = manager.getProduct(id);
            request.setAttribute("product", prod);
            request.getRequestDispatcher("Product.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductServ.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        int prodid = ((Integer) session.getAttribute("productid")).intValue();
        try {
            prod = manager.getProduct(prodid);
        } catch (SQLException ex) {
            Logger.getLogger(ProductServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (prod.getIsopen() == 0) {
            float amount = new Float (request.getParameter("amount"));
            try {
                manager.autoBid(prod.getId(), prod.getStart_price(), user.getId(), prod.getVendor(), amount, prod.getIncrease(), response.getWriter());
            } catch (Exception ex) {
                Logger.getLogger(ProductServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("User.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void check() throws SQLException {
        User user = (User) session.getAttribute("user");
        List<Product> products = manager.getUserProducts(user.getId());
        List<Purchased> purch = manager.getPurchased(user.getId());
        session.setAttribute("purchased", purch);
        session.setAttribute("offered", products);
        session.setAttribute("lost", manager.userLost(user.getId()));
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String time = formatter.format(date);
        try {
            manager.checkAuctions(((String) session.getAttribute("date")), time);
        } catch (Exception ex) {
        }
    }
}
