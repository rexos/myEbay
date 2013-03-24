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
public class UserServ extends HttpServlet {

    private List<Product> products;
    private List<String> categories;
    private DbManager manager;
    private HttpSession session;

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession(true);
        if ("ins".equals(request.getParameter("type"))) {
            //addProduct(request, response);

            response.sendRedirect("User.jsp");
        } else if ("src".equals(request.getParameter("type"))) {
            String word = request.getParameter("word");
            List<String> categories = manager.getCategories();
            request.setAttribute("categories", categories);
            products = manager.searchProducts(word);
            request.setAttribute("products", products);
            request.getRequestDispatcher("Buy.jsp").forward(request, response);
        }

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
            throws IOException, ServletException {
        session = request.getSession(true);
        try {
            check();
        } catch (SQLException ex) {
            Logger.getLogger(UserServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (request.getParameter("showbid") != null) {
            int prodid = Integer.parseInt(request.getParameter("showbid"));
            try {
                request.setAttribute("bids", manager.getBids(prodid));
                request.setAttribute("prodid", prodid);
                request.getRequestDispatcher("Bids.jsp").forward(request, response);
            } catch (Exception ex) {
            }
        } else {
            try {
                String category = null;
                category = request.getParameter("category");
                categories = manager.getCategories();
                try {
                    products = manager.getProducts(category);
                } catch (Exception ex) {
                    Logger.getLogger(UserServ.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("products", products);
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("Buy.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UserServ.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        try {
            processRequest(request, response);


        } catch (SQLException ex) {
            Logger.getLogger(UserServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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
