/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.util.ArrayList;

/**
 *
 * @author Aiden
 */
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private double balance;
    private ArrayList<Textbook> shoppingCart;
    private ArrayList<Textbook> sellingBooks;
    private boolean isAdmin;
    
    // Constructor for creating a new user
   public User(String username, String email, String password, double balance, boolean isAdmin)
   {
       //Set ID to last ID in database + 1
//       this.id = id;
       this.name = "";
       this.username = username;
       this.email = email;
       this.password = password;
       this.address = "";
       this.phoneNumber = "";
       this.balance = balance;
       this.shoppingCart = new ArrayList<Textbook>();
       this.sellingBooks = new ArrayList<Textbook>();
       this.isAdmin = isAdmin; 
   }
   
   // Constructor for updating a current user. Uses the old user object to fill in fields not
   // provided in edit dialog
   public User(User oldUser, String username, String email, String password, String name, String address, String phoneNumber)
   {
       this.id = oldUser.getId();
       this.name = name;
       this.username = username;
       this.email = email;
       this.address = address;
       this.phoneNumber = phoneNumber;
       this.balance = oldUser.getBalance();
       this.shoppingCart = oldUser.getShoppingCart();
       this.sellingBooks = oldUser.getSellingBooks();
       this.isAdmin = oldUser.getIsAdmin();
       
       // If no new password is provided, use the old user password, otherwise update it.
       if (password.equals("")) {
           this.password = oldUser.getPassword();
       }
       else {
           this.password = password;
       }
   }
    
   public int getId()
{
    return this.id;
}
    public void setId(int value)
{
     this.id = value;
}
    public String getName()
{
    return this.name;
}
    public void setName(String value)
{
     this.name = value;
}
    public String getUsername()
{
    return this.username;
}
    public void setUsername(String value)
{
     this.username = value;
}
    public String getEmail()
{
    return this.email;
}
    public void setEmail(String value)
{
     this.email = value;
}
    public String getPassword()
{
    return this.password;
}
    public void setPassword(String value)
{
     this.password = value;
}
    public String getAddress()
{
    return this.address;
}
    public void setAddress(String value)
{
     this.phoneNumber = value;
}
    public String getPhoneNumber()
{
    return this.address;
}
    public void setPhoneNumber(String value)
{
     this.phoneNumber = value;
}
     public double getBalance()
{
    return this.balance;
}
    public void setBalance(double value)
{
     this.balance = value;
}
    public ArrayList<Textbook> getShoppingCart()
{
    return this.shoppingCart;
}
    public void setShoppingCart(ArrayList<Textbook> value)
{
     this.shoppingCart = value;
}
    public ArrayList<Textbook> getSellingBooks()
{
    return this.sellingBooks;
}
    public void setSellingBooks(ArrayList<Textbook> value)
{
     this.sellingBooks = value;
}
     public boolean getIsAdmin()
{
    return this.isAdmin;
}
    public void setIsAdmin(boolean value)
{
     this.isAdmin = value;
}
    
}
