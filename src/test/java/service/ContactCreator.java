package service;

import model.Contact;
import util.StringUtils;

public class ContactCreator {
    public static final String contactAddress = "0xf33446383ce5F9310C88b0Bc3990E405564D96a2";


    public static Contact createContact(){
        return new Contact(contactAddress, StringUtils.generateRandomContactNameWithPostfixLength(6));
    }
}
