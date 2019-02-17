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
    private String seller;
    
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
    public String getSeller()
{
    return this.seller;
}
    public void setSeller(String value)
{
     this.seller = value;
}

    
    
}
