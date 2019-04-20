package com.emahaschool.EmsApp;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class EmsSqlserverDBHelper {

    // Declaring connection variables
    Connection con;


    String websiteURL,customerid,usname,fulladdress,email,contactno,productIndividualID,product_id,productname,amount,image,quantity;

    String r_product_id,user_id,productimage_id,productcart_id,productImageUrl1,productImageUrl2,qty_purchase,prod_img,amt_cart,productImageUrl3,productImageUrl4,prod_name;

    Random rndnumber = new Random();
    public int rndnumber1 = rndnumber.nextInt(1000);

    String ip = "sql5014.site4now.net/";
    String db = "DB_A44A83_EmahaSchool";
    String un = "DB_A44A83_EmahaSchool_admin";
    String pass = "admin@123";
    JSONArray productListArray = new JSONArray();
    JSONObject productListObject = new JSONObject();

    JSONArray productimageArray = new JSONArray();
    JSONObject productimageObject = new JSONObject();

    JSONObject productimageQuickObject = new JSONObject();
    JSONArray productimageQuickArray = new JSONArray();

    JSONObject cartItemsObj = new JSONObject();
    JSONArray cartItemsArray = new JSONArray();

    JSONObject wishlistItemsObj = new JSONObject();
    JSONArray wishlistItemsArray = new JSONArray();

    public int JSCounter = 0;
    public int jsoncounter = 0;


    public void EmsSqlserverDBHelper()
    {

        // Declaring Server ip, username, database name and password
        ip = "sql5014.site4now.net/";
        db = "DB_A44A83_EmahaSchool";
        un = "DB_A44A83_EmahaSchool_admin";
        pass = "admin@123";
        // Declaring Server ip, username, database name and password
//        con1 = connectionclass(un, pass, db, ip);
    }


    public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = "";
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + database + ";user=" + user+ ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }


    public boolean checkUser(String txtinput_username, String username, String password)
    {
        int rowcount = 0;
        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String query = "select * from Registration where Username = '" + username + "' and Password = '" + password +"' ";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                assert stmt != null;
                ResultSet rs = stmt.executeQuery(query);

                if(rs.next())
                {

                    customerid = rs.getString("UserRegistrationID") + rndnumber1;
                    usname = rs.getString("Username");
                    fulladdress = rs.getString("Address");
                    email = rs.getString("EmailId");
                    contactno = rs.getString("MobileNumber");

                    setCustomerid(customerid);
                    setUserName(usname);
                    setFulladdress(fulladdress);
                    setEmail(email);
                    setMobileNumber(contactno);
                    rowcount = rowcount + 1;
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        }

        if(rowcount > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean insertintoregistertion(String fname, String lname, String uname, String password,String email, String phone, String address, String userRID, String status)
    {
        int rowcount = 0;
        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String query = "insert into Registration values ('" + fname +"','" + lname +"','"+ uname +"','"+ password +"','"+ email +"','"+  phone + "','" + address + "','" + userRID + "','" + status + "')";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                PreparedStatement preparestatement = con.prepareStatement(query);
                rowcount = preparestatement.executeUpdate();
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        }


        if(rowcount > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    public boolean insertintosuggestion(String sname,String semail,String description)
    {
        int rowcount = 0;
        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else{
            String query ="insert into Suggestion values('" + sname +"','" + semail +"','"+ description +"')";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                PreparedStatement preparestatement = con.prepareStatement(query);
                rowcount = preparestatement.executeUpdate();
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        }


        if(rowcount > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean addtocartTbl(String prod_name,String userID, String txt_proID,String usercartsessionid, String qty, String cost) {

        int rowcount = 0;
        con = connectionclass(un, pass, db, ip);

        if (con == null) {

        } else {
            String querry = "insert into CartItem values('" + userID + "','" + txt_proID + "','" + usercartsessionid +"','"+ qty + "','" + cost + "')";
            Statement stmt = null;

            try {
                stmt = con.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement preparestatement = con.prepareStatement(querry);
                rowcount = preparestatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(rowcount > 0)
        {
            setProductname(prod_name);
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean addtowishlistTbl(String wprod_name,String wuserID, String txt_wproID,String qty,  String wcost) {

        int rowcount = 0;
        con = connectionclass(un, pass, db, ip);

        if (con == null) {

        } else {
            String querry = "insert into Wishlist values('" + wuserID + "','" + txt_wproID + "','" + qty + "','" + wcost + "')";
            Statement stmt = null;

            try {
                stmt = con.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement preparestatement = con.prepareStatement(querry);
                rowcount = preparestatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(rowcount > 0)
        {
            setProductname(wprod_name);
            return true;
        }
        else
        {
            return false;
        }
    }


    public String getProductdata()
    {
        websiteURL = "http://www.emahaschool.com/";
        int rowcount = 0;
        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String query = "select * from Product";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                assert stmt != null;
                ResultSet rs = stmt.executeQuery(query);

                while(rs.next())
                {

                    JSONObject productJSONObject = new JSONObject();

                    product_id = rs.getString("ProductCategoryID");
                    productname = rs.getString("ProductName");
                    amount = rs.getString("Amount");
                    productIndividualID = rs.getString("ProductID");
                    image = websiteURL + rs.getString("ProductImage");
                    quantity = rs.getString("QuantityAvailable");

                    if (JSCounter==0)
                    {
                        try{
                            productJSONObject.put("ProductCategoryID",product_id);
                            productJSONObject.put("ProductName", productname);
                            productJSONObject.put("Amount", amount);
                            productJSONObject.put("ProductID",productIndividualID);
                            productJSONObject.put("ProductImage", image);
                            productJSONObject.put("QuantityAvailable", quantity);
                            productListArray.put(productJSONObject);

                            productListObject.put("productObj", productListArray);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try
                        {
                            productJSONObject.put("ProductCategoryID",product_id);
                            productJSONObject.put("ProductName", productname);
                            productJSONObject.put("Amount", amount);
                            productJSONObject.put("ProductID",productIndividualID);
                            productJSONObject.put("ProductImage", image);
                            productJSONObject.put("QuantityAvailable", quantity);
                            productListArray.put(productJSONObject);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();

                        }
                    }
                    JSCounter++;

                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
        return productListObject.toString();
    }

    public String getProductQuick()
    {

        websiteURL = "http://www.emahaschool.com/";
        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String querygetProductQuick = "select * from Redirect";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                ResultSet rs = stmt.executeQuery(querygetProductQuick);

                while(rs.next())
                {
                    JSONObject getproductQuick = new JSONObject();

                    product_id = rs.getString("ProductID");
                    productimage_id = rs.getString("ProductCategoryID");
                    productImageUrl1 = rs.getString("ProImageUrl1");
                    productImageUrl2 = rs.getString("ProImageUrl2");
                    productImageUrl3 = rs.getString("ProImageUrl3");
                    productImageUrl4 = rs.getString("ProImageUrl4");

                    if(jsoncounter == 0)
                    {
                        try
                        {
                            getproductQuick.put("ProductID",product_id);
                            getproductQuick.put("ProductCategoryID",productimage_id);
                            getproductQuick.put("ProImageUrl1",productImageUrl1);
                            getproductQuick.put("ProImageUrl2",productImageUrl2);
                            getproductQuick.put("ProImageUrl3",productImageUrl3);
                            getproductQuick.put("ProImageUrl4",productImageUrl4);

                            productimageQuickArray.put(getproductQuick);

                            productimageQuickObject.put("imageQuickArray",productimageQuickArray);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        try
                        {
                            getproductQuick.put("ProductID",product_id);
                            getproductQuick.put("ProductCategoryID",productimage_id);
                            getproductQuick.put("ProImageUrl1",productImageUrl1);
                            getproductQuick.put("ProImageUrl2",productImageUrl2);
                            getproductQuick.put("ProImageUrl3",productImageUrl3);
                            getproductQuick.put("ProImageUrl4",productImageUrl4);

                            productimageArray.put(getproductQuick);
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }

                }
            }
            catch(Exception e)
            {

            }

        }

        return productimageQuickObject.toString();
    }


    public String retriveCartData(String usercartsessionid){



        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String querygetCartData = "select * from CartItem where UserCartSessionID='" + usercartsessionid + "' ";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                ResultSet rs = stmt.executeQuery(querygetCartData);

                while(rs.next())
                {
                    JSONObject getCartData = new JSONObject();

                    user_id = rs.getString("UserID");
                    productcart_id = rs.getString("ProductID");
                    qty_purchase = rs.getString("QuantityPurchase");
                    amt_cart = rs.getString("Amount");

                    if(jsoncounter == 0)
                    {
                        try
                        {
                            getCartData.put("UserID",user_id);
                            getCartData.put("ProductID",productcart_id);
                            getCartData.put("QuantityPurchase",qty_purchase);
                            getCartData.put("Amount",amt_cart);

                            cartItemsArray.put(getCartData);

                            cartItemsObj.put("cartItemsArrayList",cartItemsArray);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        try
                        {
                            getCartData.put("UserID",user_id);
                            getCartData.put("ProductID",productcart_id);
                            getCartData.put("QuantityPurchase",qty_purchase);
                            getCartData.put("Amount",amt_cart);

                            cartItemsArray.put(getCartData);
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }

                }
            }
            catch(Exception e)
            {

            }
        }
        return cartItemsObj.toString();
    }


    public String retriveWishlistData(){



        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String querygetCartData = "select * from Wishlist";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                ResultSet rs = stmt.executeQuery(querygetCartData);

                while(rs.next())
                {
                    JSONObject getWishlistData = new JSONObject();

                    user_id = rs.getString("UserID");
                    productcart_id = rs.getString("ProductID");
                    qty_purchase = rs.getString("QuantityPurchase");
                    amt_cart = rs.getString("Amount");

                    if(jsoncounter == 0)
                    {
                        try
                        {
                            getWishlistData.put("UserID",user_id);
                            getWishlistData.put("ProductID",productcart_id);
                            getWishlistData.put("QuantityPurchase",qty_purchase);
                            getWishlistData.put("Amount",amt_cart);

                            wishlistItemsArray.put(getWishlistData);

                            wishlistItemsObj.put("wishlistItemsArrayList",wishlistItemsArray);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        try
                        {
                            getWishlistData.put("UserID",user_id);
                            getWishlistData.put("ProductID",productcart_id);
                            getWishlistData.put("QuantityPurchase",qty_purchase);
                            getWishlistData.put("Amount",amt_cart);

                            wishlistItemsArray.put(getWishlistData);
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }

                }
            }
            catch(Exception e)
            {

            }
        }
        return wishlistItemsObj.toString();
    }


    public boolean orderplace(String userid,String usercartsessionid,String orderid,String customername,String totquantity,String totcost)
    {
        int rowcount = 0;
        con = connectionclass(un, pass, db, ip);

        if (con == null) {

        } else {
            String queryinsertorderplace = "insert into OrderPlace values('" + userid + "','" + usercartsessionid + "','" + orderid + "','" + customername + "','" + totquantity + "','" + totcost + "')";
            Statement stmt = null;

            try {
                stmt = con.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement preparestatement = con.prepareStatement(queryinsertorderplace);
                rowcount = preparestatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(rowcount > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public String getImgByProductID(String product_id)
    {
        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String querygetCartData = "select * from Product where ProductID = '" + product_id +"'";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                ResultSet rs = stmt.executeQuery(querygetCartData);

                while(rs.next())
                {

                    prod_img = rs.getString("ProductImage");



                }
            }
            catch(Exception e)
            {

            }

        }
        return prod_img;
    }


    public String getProductname(String product_id)
    {
        con = connectionclass(un, pass, db, ip);

        if(con == null)
        {

        }
        else
        {
            String querygetCartData = "select * from Product where ProductID = '" + product_id +"'";
            Statement stmt = null;

            try
            {
                stmt = con.createStatement();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                ResultSet rs = stmt.executeQuery(querygetCartData);

                while(rs.next())
                {

                    prod_name = rs.getString("ProductName");

                }
            }
            catch(Exception e)
            {

            }

        }
        return prod_name;
    }


    public boolean deleteWishlistItems(String WishlistID) {
        int statusdelWishlistItem = 0;

        String qrydelWishlistItem = null;

        con = connectionclass(un, pass, db, ip);
        if (con == null)
        {

        }
        else {

            qrydelWishlistItem = "delete from Wishlist where ProductID = " + WishlistID + " ";
        }

        try
        {
            PreparedStatement preparedStatement = con.prepareStatement(qrydelWishlistItem);
            statusdelWishlistItem = preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        if(statusdelWishlistItem > 0)

        {
            return true;
        }
        else
        {
            return false;
        }


    }

    public boolean deleteCartItems(String UserCartID)
    {
        int statusdelCartItem = 0;

        String qrydelCartItem = null;

        con = connectionclass(un, pass, db, ip);
        if (con == null)
        {

        }
        else
        {
            qrydelCartItem = "delete from CartItem where ProductID = '" + UserCartID + "' ";
        }

        try
        {
            PreparedStatement preparedStatement = con.prepareStatement(qrydelCartItem);
            statusdelCartItem = preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        if(statusdelCartItem > 0)

        {
            return true;
        }
        else
        {
            return false;
        }

    }


    public void setCustomerid(String customerid)
    {
        this.customerid = customerid;
    }

    public void setUserName(String usname)
    {
        this.usname = usname;
    }


    public void setFulladdress(String fulladdress)
    {
        this.fulladdress = fulladdress;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public void setMobileNumber(String contactno)
    {
        this.contactno = contactno;
    }


    public String getCustomerid()
    {
        return customerid;
    }

    public String getUserName()
    {
        return usname;
    }


    public String getFulladdress()
    {
        return fulladdress;
    }


    public String getEmail()
    {
        return email;
    }


    public String getContactno()
    {
        return contactno;
    }


    public void setProductname(String productname)
    {
        this.productname = productname;
    }

    public String getProductname()
    {
        return productname;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public void  setProdImgbyID(String prod_img){
        this.prod_img = prod_img;
    }

    public String getProdImgbyID(){
        return prod_img;
    }
    public void setImage(String image)
    {
        this.image = image;
    }
    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }
}




