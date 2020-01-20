package net.test.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Transaction {

    @JacksonXmlProperty(localName = "dtime", isAttribute = true)
    private String date;
    @JacksonXmlProperty(localName = "amount", isAttribute = true)
    private Double amount;
    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;
    @JacksonXmlProperty(localName = "check", isAttribute = true)
    private String check;
    @JacksonXmlProperty(localName = "vendor", isAttribute = true)
    private String vendor;
    @JacksonXmlProperty(localName = "comment", isAttribute = true)
    private String comment;
    @JacksonXmlProperty(localName = "user", isAttribute = true)
    private String user;
    @JacksonXmlProperty(localName = "confirmed", isAttribute = true)
    private boolean confirmed;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
