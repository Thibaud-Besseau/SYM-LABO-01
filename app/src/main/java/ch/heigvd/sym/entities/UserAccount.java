package ch.heigvd.sym.entities;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String email;
    private String password;
    private String avatarFilename;

    public UserAccount(String email, String password, String avatarFilename) {
        this.email = email;
        this.password = password;
        this.avatarFilename = avatarFilename;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarFilename() {
        return avatarFilename;
    }
}
