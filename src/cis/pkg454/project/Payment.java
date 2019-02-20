/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

/**
 *
 * @author Sam
 */
public class Payment {
    private int ID;
    private int textbookID;
    private int sellerID;
    private int buyerID;
    private String cardNum;
    private String cardSecuCode;
    private String cardExp;
    private String accNum;
    private String routingNum;
    private Boolean method;  // 0 for credit card, 1 for check
    private double price;
    
    // Constructor for card payments
    public Payment(int ID, int textbookID, int sellerID, int buyerID, String cardNum, String cardSecuCode, String cardExp, double price) {
        this.ID = ID;
        this.textbookID = textbookID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.cardNum = cardNum;
        this.cardSecuCode = cardSecuCode;
        this.cardExp = cardExp;
        this.price = price;
        this.method = false;
    }
    
    // Constructor for check payments
    public Payment(int ID, int textbookID, int sellerID, int buyerID, String accNum, String routingNum, double price) {
        this.ID = ID;
        this.textbookID = textbookID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.accNum = accNum;
        this.routingNum = routingNum;
        this.price = price;
        this.method = true;
    }
    
    public int getID() {
        return this.ID;
    }
    
    public int getTextbookID() {
        return this.textbookID;
    }
    
    public int getSellerID() {
        return this.sellerID;
    }
    
    public int getBuyerID() {
        return this.buyerID;
    }
    
    public String getCardNum() {
        return this.cardNum;
    }
    
    public String getCardSecuCode() {
        return this.cardSecuCode;
    }
    
    public String getCardExp() {
        return this.cardExp;
    }
    
    public String getAccNum() {
        return this.accNum;
    }
    
    public String getRoutingNum() {
        return this.routingNum;
    }
    
    public Boolean getMethod() {
        return this.method;
    }
    
    public double getPrice() {
        return this.price;
    }
}