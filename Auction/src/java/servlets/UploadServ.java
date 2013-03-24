/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import database.DbManager;
import database.Product;
import database.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
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
public class UploadServ extends HttpServlet {

    private String uploadPath = "/var/www/files";
    private HttpSession session;
    private DbManager manager;
    
    public void init(){
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
        PrintWriter out = response.getWriter();
        MultipartRequest multipartRequest = new MultipartRequest(request, uploadPath, 10 * 1024 * 1024, "ISO-8859-1", new DefaultFileRenamePolicy());
        Enumeration files = multipartRequest.getFileNames();
        session = request.getSession(true);
        Product prod = new Product();
        String photoPath = "/files/noImage.jpg";
        User user = (User) session.getAttribute("user");
        while (files.hasMoreElements()){
            String name = (String)files.nextElement();
            File file = multipartRequest.getFile(name);
            if(file != null){
            photoPath = (file.getPath()).substring(8);
            }
        }
        

        prod.setName(multipartRequest.getParameter("name"));
        prod.setQuantity(Integer.parseInt(multipartRequest.getParameter("quantity")));
        prod.setCategory(multipartRequest.getParameter("category"));
        prod.setStart_price(new Float(multipartRequest.getParameter("start_price")) );
        prod.setMin_price( new Float(multipartRequest.getParameter("min_price")) );
        prod.setIncrease(new Float(multipartRequest.getParameter("increase")));
        prod.setSend_price(new Float(multipartRequest.getParameter("send_price")));
        prod.setDescription(multipartRequest.getParameter("description"));
        prod.setPhoto(photoPath);
        prod.setStop(multipartRequest.getParameter("stop"));
        prod.setHour(multipartRequest.getParameter("hour"));
        prod.setSellerid(user.getId());
        prod.setVendor(user.getName());
        manager.addProduct(prod);
        response.sendRedirect("User.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UploadServ.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UploadServ.class.getName()).log(Level.SEVERE, null, ex);
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
