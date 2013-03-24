/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author alex
 */
public class Purchased {
    private int winnerid;
    private int sellerid;
    private int productid;
    private float price;
    private String category;
    private String name;
    private float costs;

    /**
     * @return the winnerid
     */
    public int getWinnerid() {
        return winnerid;
    }

    /**
     * @param winnerid the winnerid to set
     */
    public void setWinnerid(int winnerid) {
        this.winnerid = winnerid;
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
     * @return the productid
     */
    public int getProductid() {
        return productid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(int productid) {
        this.productid = productid;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
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
     * @return the costs
     */
    public float getCosts() {
        return costs;
    }

    /**
     * @param costs the costs to set
     */
    public void setCosts(float costs) {
        this.costs = costs;
    }
}
