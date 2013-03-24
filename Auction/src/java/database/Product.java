/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author alex
 */
public class Product {
    private String hour;
    private String vendor;
    private int sellerid;
    private int isopen;
    private String photo;
    private int id;
    private float increase;
    private String name;
    private String category;
    private String description;
    private float start_price;
    private float min_price;
    private float send_price;
    private int quantity;
    private String stop;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
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
     * @return the start_price
     */
    public float getStart_price() {
        return start_price;
    }

    /**
     * @param start_price the start_price to set
     */
    public void setStart_price(float start_price) {
        this.start_price = start_price;
    }

    /**
     * @return the min_price
     */
    public float getMin_price() {
        return min_price;
    }

    /**
     * @param min_price the min_price to set
     */
    public void setMin_price(float min_price) {
        this.min_price = min_price;
    }

    /**
     * @return the send_price
     */
    public float getSend_price() {
        return send_price;
    }

    /**
     * @param send_price the send_price to set
     */
    public void setSend_price(float send_price) {
        this.send_price = send_price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the stop
     */
    public String getStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(String stop) {
        this.stop = stop;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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

    /**
     * @return the isopen
     */
    public int getIsopen() {
        return isopen;
    }

    /**
     * @param isopen the isopen to set
     */
    public void setIsopen(int isopen) {
        this.isopen = isopen;
    }
}
