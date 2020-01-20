package net.test.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "office")
public class Office {
    @JacksonXmlProperty(localName = "updated", isAttribute = true)
    private String updated;

    @JacksonXmlProperty(localName = "account")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Account> accounts = new ArrayList<>();

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
