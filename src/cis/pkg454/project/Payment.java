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
    private int cardNum;
    private int cardSecuCode;
    private int cardExp;
    private int accNum;
    private int routingNum;
    private Boolean method;  // 0 for credit card, 1 for check
    private double price;
    
    public Payment(int ID, int textbookID, int sellerID, int buyerID, int cardNum, int cardSecuCode, int cardExp, double price) {
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
    
    public Payment(int ID, int textbookID, int sellerID, int buyerID, int accNum, int routingNum, double price) {
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
    
    public int getCardNum() {
        return this.cardNum;
    }
    
    public int getCardSecuCode() {
        return this.cardSecuCode;
    }
    
    public int getCardExp() {
        return this.cardExp;
    }
    
    public int getAccNum() {
        return this.accNum;
    }
    
    public int getRoutingNum() {
        return this.routingNum;
    }
    
    public Boolean getMethod() {
        return this.method;
    }
    
    public double getPrice() {
        return this.price;
    }
}