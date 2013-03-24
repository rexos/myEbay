/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DbManager;
import database.Purchased;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author alex
 */
public class ExcelGenerator extends HttpServlet {

    private DbManager manager;
    private HttpSession session;
    List<Purchased> closed;

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
        session = request.getSession(true);
        closed = (List<Purchased>) session.getAttribute("closed");
        OutputStream out = null;
        try {

            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition",
                    "attachment; filename=closed_auctions.xls");

            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("Demo", 0);
            Iterator iter = closed.iterator();
            Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String today = formatter.format(date);
            formatter = new SimpleDateFormat("HH:mm:ss");
            String time = formatter.format(date);
            s.addCell(new Label(0, 0, "Created on:"));
            s.addCell(new Label(2, 0, today));
            s.addCell(new Label(4, 0, time));
            s.addCell(new Label(0, 2, "Item ID"));
            s.addCell(new Label(2, 2, "Item Name"));
            s.addCell(new Label(4, 2, "Price"));
            s.addCell(new Label(6, 2, "Auction Costs"));
            s.addCell(new Label(8, 2, "Seller ID"));
            int j = 4;
            while (iter.hasNext()) {
                Purchased p = (Purchased) iter.next();
                s.addCell(new Label(0, j, "" + p.getProductid()));
                s.addCell(new Label(2, j, "" + p.getName()));
                s.addCell(new Label(4, j, "" + p.getPrice()));
                s.addCell(new Label(6, j, "" + p.getCosts()));
                s.addCell(new Label(8, j, "" + p.getSellerid()));
                j+=2;
            }

            w.write();
            w.close();

        } catch (Exception e) {
            throw new ServletException("Exception in Excel Sample Servlet", e);
        } finally {
            if (out != null) {
                out.close();
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
