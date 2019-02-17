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
public class Textbook {
    private String name;
    private double price;
    private String author;
    private int isbn;
    private int id;
    private int sellerID;
    
    public void Textbook(String name, double price, String author, int isbn) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.isbn = isbn;
        // Fill in once database is available
//        this.id = last id in database + 1
//        this.sellerID = currentUser.ID
    }
    
    public String getName()
{
    return this.name;
}
    public void setName(String value)
{
     this.name = value;
}
    public double getPrice()
{
    return this.price;
}
    public void setPrice(double value)
{
     this.price = value;
}
    public String getAuthor()
{
    return this.author;
}
    public void setAuthor(String value)
{
     this.author = value;
}
    public int getId()
{
    return this.id;
}
    public void setId(int value)
{
     this.id = value;
}
    public int getIsbn()
{
    return this.isbn;
}
    public void setIsbn(int value)
{
     this.isbn = value;
}
    public int getSellerID()
{
    return this.sellerID;
}

    
    
}
