package dav.com.foody.Objects;

import java.io.Serializable;

/**
 * Created by binhb on 04/05/2017.
 */

public class User implements Serializable {
    private Integer id;
    private String avatar;
    private String email;
    private String gender;
    private String name;
    private String password;
    private String value;

    public User() {
    }

    public User(Integer id, String avatar, String email, String gender, String name, String password, String value) {
        this.id = id;
        this.avatar = avatar;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
