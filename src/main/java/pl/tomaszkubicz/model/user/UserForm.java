package pl.tomaszkubicz.model.user;

import pl.tomaszkubicz.model.article.ArticleMySQLForm;
import pl.tomaszkubicz.model.user.enums.UserRole;
import pl.tomaszkubicz.model.user.enums.UserSex;

import javax.persistence.Lob;


public class UserForm {

    private String username;
    private UserSex userSex;
    private UserRole userRole;
    private String password;

    public UserForm(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private UserForm(Builder builder) {
        this.username = builder.username;
        this.userSex= builder.userSex;
        this.userRole = builder.userRole;
        this.password = builder.password;
    }


    public static class Builder{
        private String username;
        private UserSex userSex;
        private UserRole userRole;
        private String password;


        public Builder (String username, UserSex userSex, UserRole userRole, String password){
            this.username = username;
            this.userSex = userSex;
            this.userRole = userRole;
            this.password = password;
        }


        public UserForm build(){
            return new UserForm(this);
        }
    }



}