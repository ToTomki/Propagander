package pl.tomaszkubicz.model.user;

import pl.tomaszkubicz.model.user.enums.UserRole;
import pl.tomaszkubicz.model.user.enums.UserSex;


public class UserForm {

    private String userName;
    private UserSex userSex;
    private UserRole userRole;
    private String userPassword;

    public UserForm(){};

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    private UserForm(Builder builder) {
        this.userName = builder.userName;
        this.userSex= builder.userSex;
        this.userRole = builder.userRole;
        this.userPassword = builder.userPassword;
    }


    public static class Builder{
        private String userName;
        private UserSex userSex;
        private UserRole userRole;
        private String userPassword;

        public Builder (String userName, UserSex userSex, UserRole userRole, String userPassword){
            this.userName = userName;
            this.userSex = userSex;
            this.userRole = userRole;
            this.userPassword = userPassword;
        }


        public UserForm build(){
            return new UserForm(this);
        }
    }



}