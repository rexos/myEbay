/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Mail.Mail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 *
 * @author alex
 */
public class DbManager {

    Mail mail;
    public Connection dbcon;
    //private transient Connection dbcon;

    public DbManager() throws SQLException {
        mail = new Mail(this);
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/auction", "postgres", "postgres");
        this.dbcon = con;

    }

    public static void shutdown() {
        try {
            DriverManager.getConnection("jdbc:postgresql://localhost/auction;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).info(ex.getMessage());
        }
    }

    public void createUser(User user) throws SQLException {
        PreparedStatement stm = this.dbcon.prepareStatement("INSERT INTO customer(username,password,address,mail,type,first_name,second_name) VALUES (?,?,?,?,?,?,?)");
        stm.setString(1, user.getUsername());
        stm.setString(2, user.getPassword());
        stm.setString(3, user.getAddress());
        stm.setString(4, user.getMail());
        stm.setString(5, user.getType());
        stm.setString(6, user.getName());
        stm.setString(7, user.getSurname());
        stm.executeUpdate();
        stm.close();
    }

    public User authenticate(String username, String password) throws SQLException {
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM customer WHERE username =  ? AND  password =  ?");
        try {
            stm.setString(1, username);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            try {
                if (rs.next()) {
                    User user = new User();
                    user.setName(rs.getString("first_name"));
                    user.setSurname(rs.getString("second_name"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setAddress(rs.getString("address"));
                    user.setMail(rs.getString("mail"));
                    user.setId(rs.getInt("userid"));
                    user.setType(rs.getString("type"));
                    return user;
                } else {
                    return null;
                }
            } finally {
                rs.close();
            }

        } finally {
            stm.close();
        }
    }

    public List<Product> searchProducts(String words) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM products WHERE  description ~ ? OR name ~ ?");
        stm.setString(1, words);
        stm.setString(2, words);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            setUpProd(p, rs);
            products.add(p);
        }
        return products;
    }

    public List<Product> getProducts(String category) throws SQLException, AddressException, MessagingException {
        List<Product> products = new ArrayList<Product>();
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM products");
        try {
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Product p = new Product();

                    setUpProd(p, rs);
                    if (category == null || category.equals("See_All") || p.getCategory().equals(category)) {
                        products.add(p);
                    }

                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return products;
    }

    private void setUpProd(Product p, ResultSet rs) throws SQLException {
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setCategory(rs.getString("category"));
        p.setMin_price(rs.getFloat("min_price"));
        p.setQuantity(rs.getInt("quantity"));
        p.setStart_price(rs.getFloat("start_price"));
        p.setSend_price(rs.getFloat("send_price"));
        p.setSellerid(rs.getInt("sellerid"));
        p.setStop(rs.getString("stop_auction"));
        p.setPhoto(rs.getString("photo"));
        p.setIncrease(rs.getFloat("increase"));
        p.setDescription(rs.getString("description"));
        p.setVendor(rs.getString("vendor"));
        p.setHour(rs.getString("hour"));
        p.setIsopen(rs.getInt("isopen"));
    }

    public void addProduct(Product prod) throws SQLException, IOException {
        PreparedStatement stm = dbcon.prepareStatement("INSERT INTO products (quantity,category,start_price,min_price,increase,send_price,stop_auction,name,description,sellerid,photo,vendor,hour) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stm.setInt(1, prod.getQuantity());
        stm.setString(2, prod.getCategory());
        stm.setFloat(3, prod.getStart_price());
        stm.setFloat(4, prod.getMin_price());
        stm.setFloat(5, prod.getIncrease());
        stm.setFloat(6, prod.getSend_price());
        stm.setString(7, prod.getStop());
        stm.setString(8, prod.getName());
        stm.setString(9, prod.getDescription());
        stm.setInt(10, prod.getSellerid());
        stm.setString(11, prod.getPhoto());
        stm.setString(12, prod.getVendor());
        stm.setString(13, prod.getHour());
        stm.executeUpdate();
        stm.close();

    }

    public List<String> getCategories() throws SQLException {
        List<String> categories = new ArrayList<String>();
        PreparedStatement stm = dbcon.prepareStatement("SELECT DISTINCT category FROM products");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            String cat = rs.getString("category");
            categories.add(cat);
        }
        stm.close();
        return categories;
    }

    public Product getProduct(int id) throws SQLException {
        Product prod = new Product();
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM products WHERE id = ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            setUpProd(prod, rs);
        }
        rs.close();
        stm.close();
        return prod;

    }

    private void addOffer(int prodid, float prodstart, int userid, String vendor, float max, float increase) throws SQLException {
        PreparedStatement stm = dbcon.prepareStatement("INSERT INTO bids(prodid,current,userid,vendor,maximum,increase) VALUES (?,?,?,?,?,?)");
        stm.setInt(1, prodid);
        stm.setFloat(2, prodstart);
        stm.setInt(3, userid);
        stm.setString(4, vendor);
        stm.setFloat(5, max);
        stm.setFloat(6, increase);
        stm.executeUpdate();
        stm.close();

    }

    private String getMail(int id) throws SQLException {
        String mail = null;
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM customer WHERE userid = ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            mail = rs.getString("mail");
        }
        rs.close();
        stm.close();
        return mail;
    }

    public void autoBid(int prodid, float prodstart, int newuserid, String vendor, float newmax, float increase, PrintWriter out) throws SQLException, AddressException, MessagingException {

        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM bids WHERE prodid = ? AND maximum = (SELECT MAX(maximum) FROM bids WHERE prodid = ? GROUP BY prodid)");

        int maxid = 0;
        float newcurrent = 0;
        float maxcurrent = 0;
        float maxmaximum = 0;
        stm.setInt(1, prodid);
        stm.setInt(2, prodid);
        ResultSet rs = stm.executeQuery();
        if (rs.next() == false) {
            addOffer(prodid, prodstart, newuserid, vendor, newmax, increase);
            String mess = "You are now the best offerer on Item number : ";
            mail.message(getMail(newuserid), prodid, mess, newuserid);

        } else {
            rs = stm.executeQuery();
            while (rs.next()) {
                maxid = rs.getInt("userid");
                maxcurrent = rs.getFloat("current");
                maxmaximum = rs.getFloat("maximum");
            }
            stm.close();

            if (newmax > maxmaximum) {
                newcurrent = maxmaximum + increase;
                maxcurrent = maxmaximum;
                addOffer(prodid, maxcurrent, maxid, vendor, maxmaximum, increase);
                addOffer(prodid, newcurrent, newuserid, vendor, newmax, increase);
                mail.message(getMail(maxid), prodid, null, maxid);
                String mess = "You are now the best offerer on Item number : ";
                mail.message(getMail(newuserid), prodid, mess, newuserid);
                //mail to old
            } else if (newmax < maxmaximum) {
                if (newmax <= maxcurrent) {
                    addOffer(prodid, newmax, newuserid, vendor, newmax, increase);
                    mail.message(getMail(newuserid), prodid, null, newuserid);
                    //mail to new
                } else if (newmax > maxcurrent) {
                    newcurrent = newmax;
                    maxcurrent = newmax + increase;
                    addOffer(prodid, newcurrent, newuserid, vendor, newmax, increase);
                    addOffer(prodid, maxcurrent, maxid, vendor, maxmaximum, increase);
                    mail.message(getMail(newuserid), prodid, null, newuserid);
                    //mail to new
                }
            } else {
                newmax = newmax - 1;
                newcurrent = newmax;
                maxcurrent = maxmaximum;
                addOffer(prodid, newcurrent, newuserid, vendor, newmax, increase);
                addOffer(prodid, maxcurrent, maxid, vendor, maxmaximum, increase);
                mail.message(getMail(newuserid), prodid, null, newuserid);
                //mail to new
            }
        }
    }

    public String getUserName(int id) throws SQLException {
        String name = null;
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM customer WHERE userid= ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            name = rs.getString("first_name");
        }
        return name;
    }

    public List<Product> getUserProducts(int userid) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        PreparedStatement stm = dbcon.prepareStatement("SELECT id,name,category,start_price FROM products WHERE sellerid = ?");
        stm.setInt(1, userid);
        try {
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setCategory(rs.getString("category"));
                    p.setStart_price(rs.getInt("start_price"));
                    products.add(p);
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        return products;
    }

    public void checkAuctions(String date, String time) throws SQLException, AddressException, MessagingException {
        mail = new Mail(this);
        boolean setzero = false;
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM products WHERE isopen=0 AND stop_auction <= ? ");

        stm.setString(1, date);
        ResultSet rs = stm.executeQuery();
        // START========================================  

        while (rs.next()) {
            float current = 0;
            String closeday = rs.getString("stop_auction");
            String hour = rs.getString("hour");
     
            int val1 = closeday.compareTo(date);
            int val2 = hour.compareTo(time);
            if (val1 < 0 || (val1 == 0 && val2 <= 0)) {
                float costs = (float) 1.23;
                int prodid = rs.getInt("id");
                int sellerid = rs.getInt("sellerid");
                float min_price = rs.getFloat("min_price");
                float send_price = rs.getFloat("send_price");
                
                String category = rs.getString("category");
                String name = rs.getString("name");
                int winnerid = 0;
                float finalprice = 0;
                float maximum = 0;
                
                //STM2 who's the winner?
                PreparedStatement stm2 = dbcon.prepareStatement("SELECT userid,current,maximum FROM bids WHERE prodid = ? AND maximum = (SELECT MAX(maximum) FROM bids WHERE prodid = ?)");
                stm2.setInt(1, prodid);
                stm2.setInt(2, prodid);
                ResultSet rs2 = stm2.executeQuery();
                while (rs2.next()) {
                    winnerid = rs2.getInt("userid");
                    current = rs2.getFloat("current");
                    maximum = rs2.getFloat("maximum");
                    if (current < min_price && maximum >= min_price){
                        current = min_price;
                    }
                    finalprice = current + send_price;
                }

                rs2.close();
                stm2.close();

                if (winnerid > 0 && current >= min_price) {
                    costs = ((float) (finalprice / 100 * 1.25));
                    float temp = (int) costs;
                    temp += 0.5;
                    if (costs > temp) {
                        costs = ((float) (temp + 0.5));
                    } else if (costs <= temp) {
                        costs = temp;
                    }
                    String mess = "You won the auction on the item number : ";
                    mail.message(getMail(winnerid), prodid, mess, winnerid);
                } else {
                    String mess = "You have to pay 1,23 Euros because your auction has been closed without bids on Item number : ";
                    mail.message(getMail(sellerid), prodid, mess, sellerid);
                    setzero = true;

                }
                // STM 3
                PreparedStatement stm3 = dbcon.prepareStatement("INSERT INTO closed(winnerid,sellerid,productid,price,category,name,costs) VALUES(?,?,?,?,?,?,?)");
                stm3.setInt(1, winnerid);
                stm3.setInt(2, sellerid);
                stm3.setInt(3, prodid);
                stm3.setFloat(4, finalprice);
                stm3.setString(5, category);
                stm3.setString(6, name);
                stm3.setFloat(7, costs);

                stm3.executeUpdate();

                if (setzero) {
                    //delete supposed bids
                    PreparedStatement temp = dbcon.prepareStatement("UPDATE closed SET winnerid=0 WHERE productid =?");
                    temp.setInt(1, prodid);
                    temp.executeUpdate();
                    temp.close();
                    setzero = false;
                }
                //STM4
                PreparedStatement stm4 = dbcon.prepareStatement("DELETE FROM products WHERE id = ?");
                stm4.setInt(1, prodid);
                stm4.executeUpdate();
                stm4.close();
            }
            //END==================================================       
        }
        rs.close();
        stm.close();

    }

    public List<Purchased> getPurchased(int userid) throws SQLException {
        List<Purchased> purch = new ArrayList<Purchased>();
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM closed WHERE winnerid = ?");
        stm.setInt(1, userid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Purchased p = new Purchased();
            p.setWinnerid(rs.getInt("winnerid"));
            p.setSellerid(rs.getInt("sellerid"));
            p.setProductid(rs.getInt("productid"));
            p.setPrice(rs.getFloat("price"));
            p.setCategory(rs.getString("category"));
            p.setName(rs.getString("name"));
            p.setCosts(rs.getFloat("costs"));
            purch.add(p);
        }
        rs.close();
        stm.close();
        return purch;
    }

    public List<Auction> getCurrentAuctions() throws SQLException {
        List<Auction> auctions = new ArrayList<Auction>();
        PreparedStatement stm = dbcon.prepareStatement("select p.sellerid,p.id,p.description,max(current) as current from products p left join bids b on p.id = b.prodid where p.isopen=0 group by p.sellerid,p.id,p.description");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Auction auct = new Auction();
            auct.setVendor("bah");
            auct.setSellerid(rs.getInt("sellerid"));
            auct.setProdid(rs.getInt("id"));
            auct.setDescription(rs.getString("description"));
            float current = rs.getFloat("current");
            
            auct.setCurrentPrice(current);
            auctions.add(auct);
        }
        rs.close();
        stm.close();
        return auctions;
    }

    public void removeAuction(int prodid, String mess) throws SQLException, AddressException, MessagingException {
        PreparedStatement stm = dbcon.prepareStatement("UPDATE products SET isopen = 1 WHERE id = ?");
        PreparedStatement stm2 = dbcon.prepareStatement("SELECT DISTINCT userid FROM bids WHERE prodid = ?");
        stm.setInt(1, prodid);
        stm2.setInt(1, prodid);
        ResultSet rs = stm2.executeQuery();
        while (rs.next()) {
            int userid = rs.getInt("userid");
            String message = "Admin message : " + mess + "\n Auction closed on Item number : ";
            mail.message(getMail(userid), prodid, message, userid);
        }
        stm.executeUpdate();
        stm2.close();
        rs.close();
        stm.close();
    }

    public List<Purchased> getAuctionClosed() throws SQLException {
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM closed");
        List<Purchased> purch = new ArrayList<Purchased>();
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Purchased p = new Purchased();
            p.setSellerid(rs.getInt("sellerid"));
            p.setProductid(rs.getInt("productid"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getFloat("price"));
            p.setCosts(rs.getFloat("costs"));
            purch.add(p);
        }
        return purch;
    }

    public List<Bid> getBids(int prodid) throws SQLException {
        List<Bid> bids = new ArrayList<Bid>();
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM bids WHERE prodid = ?");
        stm.setInt(1, prodid);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            Bid b = new Bid();
            b.setProdid(rs.getInt("prodid"));
            b.setCurrent(rs.getFloat("current"));
            b.setUserid(rs.getInt("userid"));
            b.setVendor(rs.getString("vendor"));
            b.setMaximum(rs.getFloat("maximum"));
            b.setIncrease(rs.getFloat("increase"));
            b.setDate(rs.getString("date"));
            b.setHour(rs.getString("hour").substring(0, 8));
            bids.add(b);
        }


        return bids;
    }
    /**
     * 
     * @param userid is the id on which search the lost auctions
     * @return lost list of auctions lost by the user
     * @throws SQLException 
     */
    public List<Purchased> userLost(int userid) throws SQLException {
        List<Purchased> lost = new ArrayList<Purchased>();
        PreparedStatement stm = dbcon.prepareStatement("SELECT * FROM closed c JOIN (SELECT DISTINCT prodid, userid FROM bids) b ON c.productid = b.prodid WHERE c.winnerid != ? AND c.sellerid != ? AND b.userid = ? ");
        stm.setInt(1, userid);
        stm.setInt(2, userid);
        stm.setInt(3, userid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Purchased p = new Purchased();
            p.setSellerid(rs.getInt("sellerid"));
            p.setProductid(rs.getInt("productid"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getFloat("price"));
            p.setCosts(rs.getFloat("costs"));
            lost.add(p);
        }
        return lost;
    }
}
