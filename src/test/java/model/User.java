package model;

import java.util.Objects;

public class User {

    private String keyPhrase;
    private String password;


    public User(String password, String keyPhrase) {
        this.keyPhrase = keyPhrase;
        this.password = password;
    }

    public String getKeyPhrase() {
        return keyPhrase;
    }

    public void setKeyPhrase(String username) {
        this.keyPhrase = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + keyPhrase + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getKeyPhrase(), user.getKeyPhrase()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyPhrase(), getPassword());
    }
}
