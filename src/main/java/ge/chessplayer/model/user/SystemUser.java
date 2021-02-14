package ge.chessplayer.model.user;

import javax.persistence.*;
import java.util.List;

public class SystemUser {

    public static final int STARTING_RATING = 1200;


    long id;

    String username;

    String password;

    Integer rating = STARTING_RATING;

    String firstName;

    String lastName;

    List<SystemUser> friends;

    public static int getStartingRating() {
        return STARTING_RATING;
    }

    public List<SystemUser> getFriends() {
        return friends;
    }

    public void setFriends(List<SystemUser> friends) {
        this.friends = friends;
    }

    public SystemUser() {

    }

    public SystemUser(String username, String password, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
