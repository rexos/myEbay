/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author alex
 */
public class Auction {
    private String vendor;
    private String description;
    private float currentPrice;
    private int sellerid;
    private int prodid;

    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the currentPrice
     */
    public float getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @param currentPrice the currentPrice to set
     */
    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * @return the sellerid
     */
    public int getSellerid() {
        return sellerid;
    }

    /**
     * @param sellerid the sellerid to set
     */
    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    /**
     * @return the prodid
     */
    public int getProdid() {
        return prodid;
    }

    /**
     * @param prodid the prodid to set
     */
    public void setProdid(int prodid) {
        this.prodid = prodid;
    }
    
}
