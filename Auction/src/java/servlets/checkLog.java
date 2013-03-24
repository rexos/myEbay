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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alex
 */
public class checkLog extends HttpServlet {
    
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
            throws ServletException, IOException, SQLException, AddressException, MessagingException{
        if ("log".equals(request.getParameter("type"))) {
            session = request.getSession(true);
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = manager.authenticate(username, password);
            
            if (user == null) {
                session.invalidate();
                response.sendRedirect("Login.html");
            } else {
                
                Cookie cookie1 = new Cookie("username", username);
                cookie1.setMaxAge(-1);
                Cookie cookie2 = new Cookie("password", password);
                cookie2.setMaxAge(-1);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                
                
                session.setAttribute("user", user);
                
                Date date = Calendar.getInstance().getTime();
                DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                String today = formatter.format(date);
                formatter = new SimpleDateFormat("HH:mm:ss");
                String time = formatter.format(date);
                manager.checkAuctions(today, time);
                session.setAttribute("date", today);
                if ("admin".equals(user.getType())) {
                    request.getRequestDispatcher("AdminServ").forward(request, response);
                } else {
                    List<Product> products = manager.getUserProducts(user.getId());
                    List<Purchased> purch = manager.getPurchased(user.getId());
                    session.setAttribute("purchased", purch);
                    session.setAttribute("offered", products);
                    session.setAttribute("lost", manager.userLost(user.getId()));
                    response.sendRedirect("User.jsp");
                }
            }
        } else {
            User user = new User();
            user.setName(request.getParameter("first_name"));
            user.setSurname(request.getParameter("second_name"));
            user.setUsername(request.getParameter("name"));
            user.setPassword(request.getParameter("pswd"));
            user.setAddress(request.getParameter("address"));
            user.setMail(request.getParameter("mail"));
            user.setType("simple");
            user.capitalize();
            manager.createUser(user);
            session = request.getSession(true);
            session.invalidate();
            response.sendRedirect("Login.html");
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
            throws ServletException, IOException {
        session = request.getSession(true);
        Cookie[] cookies = request.getCookies();
        for(int i=0; i< cookies.length; i++){
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
        session.invalidate();
        response.sendRedirect("Login.html");
        
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
                    try {  
                        processRequest(request, response);
                    } catch (Exception ex) {}
        } catch (Exception ex) {
            Logger.getLogger(checkLog.class.getName()).log(Level.SEVERE, null, ex);
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
}
