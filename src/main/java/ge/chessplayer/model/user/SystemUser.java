package ge.chessplayer.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class SystemUser implements UserDetails {

    public static final int STARTING_RATING = 1200;


    long id;

    String username;

    String password;

    Integer rating = STARTING_RATING;

    String firstName;

    String lastName;

    SystemUserRole role = SystemUserRole.USER;

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    public SystemUserRole getRole() {
        return role;
    }

    public void setRole(SystemUserRole role) {
        this.role = role;
    }
}
