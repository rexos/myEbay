/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author alex
 */
public class Bid {
   private int prodid;
   private float current;
   private int userid;
   private String vendor;
   private float maximum;
   private float increase;
   private String date;
   private String hour;

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

    /**
     * @return the current
     */
    public float getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(float current) {
        this.current = current;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

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
     * @return the maximum
     */
    public float getMaximum() {
        return maximum;
    }

    /**
     * @param maximum the maximum to set
     */
    public void setMaximum(float maximum) {
        this.maximum = maximum;
    }

    /**
     * @return the increase
     */
    public float getIncrease() {
        return increase;
    }

    /**
     * @param increase the increase to set
     */
    public void setIncrease(float increase) {
        this.increase = increase;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }
}
