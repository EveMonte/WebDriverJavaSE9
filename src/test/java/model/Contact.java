package model;

import java.util.Objects;

public class Contact {
    private String contactAddress;
    private String contactName;
    private String actualContactName;

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getActualContactName() {
        return actualContactName;
    }

    public void setActualContactName(String actualContactName) {
        this.actualContactName = actualContactName;
    }

    public Contact(String contactAddress, String contactName) {
        this.contactAddress = contactAddress;
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactAddress='" + contactAddress + '\'' +
                ", contactName='" + contactName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactAddress, contact.contactAddress) && Objects.equals(contactName, contact.contactName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactAddress, contactName);
    }
}
