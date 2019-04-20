package com.emahaschool.EmsApp;

public class WishListItems
{

    public String titleCart,priceCart,productIMG,prod_id;

    public void settitlecart(String titleCart) {
        this.titleCart = titleCart;
    }

    public String gettitlecart()
    {
        return titleCart;
    }

    public void setpriceCart(String priceCart)
    {
        this.priceCart = priceCart;
    }

    public String getpriceCart()
    {
        return priceCart;
    }

    public void setproductIMG(String productIMG)
    {
        this.productIMG = productIMG;
    }

    public String getproductIMG()
    {
        return productIMG;
    }

    public void setproduct_ID(String prod_id) { this.prod_id =  prod_id;}

    public String getproduct_ID(){ return prod_id; }

}
