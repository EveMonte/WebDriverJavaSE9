package service;

import model.User;

public class UserCreator {

    public static final String TESTDATA_KEY_PHRASE = "question until unfold mystery predict surge whale crumble dress arrange hello wife";
    public static final String TESTDATA_USER_PASSWORD = "rm.dthnb";

    public static User withCredentialsFromProperty(){
        return new User(TESTDATA_USER_PASSWORD,
                TESTDATA_KEY_PHRASE);
    }
}
