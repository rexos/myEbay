/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import database.DbManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author alex
 */
public class Listener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        try {
            DbManager manager = new DbManager();
            sce.getServletContext().setAttribute("dbmanager", manager);
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).severe(ex.toString());
            throw new RuntimeException(ex);
        }


    }

    public void contextDestroyed(ServletContextEvent sce) {
        DbManager manager = (DbManager) sce.getServletContext().getAttribute("dbmanager");
        manager.shutdown();
    }
}
