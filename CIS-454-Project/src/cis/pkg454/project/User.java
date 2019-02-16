/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

/**
 *
 * @author Aiden
 */
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String address;
    private double balance;
    private Textbook [] shoppingCart;
    private Textbook [] sellingBooks;
    private boolean isAdmin;
    
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
     this.address = value;
}
    public Textbook [] getShoppingCart()
{
    return this.shoppingCart;
}
    public void setBalance(Textbook [] value)
{
     this.shoppingCart = value;
}
    public Textbook [] getSellingBooks()
{
    return this.sellingBooks;
}
    public void setSellingBooks(Textbook [] value)
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
