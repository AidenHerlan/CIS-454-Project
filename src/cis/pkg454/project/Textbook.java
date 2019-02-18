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
    private String isbn;
    private int id;
    private int sellerID;
    
    
    // Constructor for making a new textbook, when an item is being added to the marketplace
    public Textbook(String name, double price, String author, String isbn) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.isbn = isbn;
        // Fill in once database is available
//        this.id = last id in database + 1
//        this.sellerID = currentUser.ID
    }
    
    // Constructor for modifying a textbook post
    public Textbook(Textbook oldBook, String name, double price, String author, String isbn) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.isbn = isbn;
        this.id = oldBook.getId();
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
    public String getIsbn()
{
    return this.isbn;
}
    public void setIsbn(String value)
{
     this.isbn = value;
}
    public int getSellerID()
{
    return this.sellerID;
}

    
    
}
