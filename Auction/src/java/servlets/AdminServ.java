/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.Auction;
import database.DbManager;
import database.Purchased;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class AdminServ extends HttpServlet {

    private DbManager manager;
    private HttpSession session;
    private List<Purchased> closed;

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
        closed = new ArrayList<Purchased>();
        try {
            closed = manager.getAuctionClosed();
        } catch (SQLException ex) {
        }
        session.setAttribute("closed", closed);
        response.sendRedirect("Admin.jsp");
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
        closed = new ArrayList<Purchased>();
        if (request.getParameter("auctionid") != null) {
            int id = Integer.parseInt(request.getParameter("auctionid"));
            try {
                String mess = request.getParameter("message");
                try {
                    manager.removeAuction(id, mess);
                } catch (Exception ex) {
                    Logger.getLogger(AdminServ.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    closed = manager.getAuctionClosed();
                } catch (SQLException ex) {
                }
                session.setAttribute("closed", closed);
                response.sendRedirect("Admin.jsp");
            } catch (Exception e) {
            }
        } else {
            List<Auction> auctions = new ArrayList<Auction>();
            //session = request.getSession(true);
            try {
                auctions = manager.getCurrentAuctions();
            } catch (SQLException ex) {
                Logger.getLogger(AdminServ.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("auctions", auctions);
            try {
                closed = manager.getAuctionClosed();
            } catch (SQLException ex) {
            }
            session.setAttribute("closed", closed);
            response.sendRedirect("Admin.jsp");
        }
        //processRequest(request, response);
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
