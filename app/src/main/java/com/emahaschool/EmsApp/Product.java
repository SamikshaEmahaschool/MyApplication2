package com.emahaschool.EmsApp;

public class Product
{

    private String product_name;
    private int product_cost;
    private String thumbnail;
    private String productID;
    private String r_product_id;
//    private String productindividualID;

    public Product()
    {

    }

    public Product(String product_name, int product_cost, String thumbnail, String productID){

        this.product_name = product_name;
        this.product_cost = product_cost;
        this.thumbnail = thumbnail;
//        this.productindividualID = product_id;
//        this.product_id = product_id;
    }

//    public void setProductID(String productID)
//    {
//        this.productindividualID = productID;
//    }

//    public String getProductID()
//    {
//        return productindividualID;
//    }

    public String getproduct_name()
    {
        return product_name;
    }

    public void setproduct_name(String name) {
        this.product_name = name;
    }

    public int getproduct_cost() {
        return product_cost;
    }

    public void setproduct_cost(int product_cost) {
        this.product_cost = product_cost;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  String getproduct_ID()
    {
        return productID;
    }

    public void setproduct_ID(String productID)
    {
        this.productID = productID;
    }

    public String getproductQuick()
    {
        return r_product_id;
    }

    public void setproductQuick(String rProductID){
        this.r_product_id = r_product_id;
    }



}
