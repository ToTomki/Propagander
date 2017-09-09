package pl.tomaszkubicz.model.user;

import pl.tomaszkubicz.model.user.enums.UserRole;
import pl.tomaszkubicz.model.user.enums.UserSex;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table
public class User {

    @Id
    @Column(columnDefinition = "INT(7) UNSIGNED", name="Id") // UNSIGNED means int>-1, so there's bigger possible number
    @GeneratedValue // automatycznie generuje unikatową wartość dla tego pola
    private Long userId;
    @Column(name="nickname")
    private String userName;
    @Enumerated(EnumType.STRING)
    @Column(name="płeć")
    private UserSex userSex;
    @Enumerated(EnumType.STRING)
    @Column(name="rola")
    private UserRole userRole;
    @Column(name="hasło")
    private String userPassword;
    @Column(name="ostatni_komentarz")
    private Timestamp userLastComment;

    public User(Long userId, String userName, UserSex userSex, UserRole userRole, String userPassword, Timestamp userLastComment) {
        this.userId = userId;
        this.userName = userName;
        this.userSex = userSex;
        this.userRole = userRole;
        this.userPassword = userPassword;
        this.userLastComment = userLastComment;
    }

    public User(){};

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSsex) {
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

    public Timestamp getUserLastComment() {return userLastComment;}

    public void setUserLastComment(Timestamp userLastComment) {        this.userLastComment = userLastComment;}

    public User(UserForm userForm){
        this.userName = userForm.getUserName();
        this.userSex = userForm.getUserSex();
        this.userRole = userForm.getUserRole();
        this.userPassword = userForm.getUserPassword();
        this.userLastComment = Timestamp.valueOf(LocalDateTime.now());
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userRole=" + userRole +
                ", userPassword='" + userPassword + '\'' +
                ", userLastComment=" + userLastComment +
                '}';
    }

}
