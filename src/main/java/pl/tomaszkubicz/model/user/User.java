package pl.tomaszkubicz.model.user;

import pl.tomaszkubicz.model.article.ArticleComment;
import pl.tomaszkubicz.model.user.enums.UserRole;
import pl.tomaszkubicz.model.user.enums.UserSex;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc

@Entity
@Table
public class User {

    @Id
    @Column(columnDefinition = "INT(7) UNSIGNED", name="Id") // UNSIGNED means int>-1, so there's bigger possible number
    @GeneratedValue // automatycznie generuje unikatową wartość dla tego pola
    private Long userId;
    @Column(name="nickname")
    private String username;
    @Enumerated(EnumType.STRING)
    @Column(name="sex")
    private UserSex userSex;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private UserRole userRole;
    @Column(name="password")
<<<<<<< HEAD
    private String password;
    @Column(name="last_comment")
    private Timestamp userLastComment;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @Transient
    private ArrayList<ArticleComment> userComments;

    public User(Long userId, String username, UserSex userSex, UserRole userRole, String password, Timestamp userLastComment, ArrayList<ArticleComment> userComments) {
=======
    private String userPassword;
<<<<<<< HEAD
    @Column(name="last_comment")
=======
    @Column(name="ostatni_komentarz")
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819
    private Timestamp userLastComment;

    public User(Long userId, String userName, UserSex userSex, UserRole userRole, String userPassword, Timestamp userLastComment) {
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
        this.userId = userId;
        this.username = username;
        this.userSex = userSex;
        this.userRole = userRole;
<<<<<<< HEAD
        this.password = password;
        this.userLastComment = userLastComment;
        this.userComments = userComments;
=======
        this.userPassword = userPassword;
        this.userLastComment = userLastComment;
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
    }

    public User(){};

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getUserLastComment() {return userLastComment;}

    public void setUserLastComment(Timestamp userLastComment) {        this.userLastComment = userLastComment;}

<<<<<<< HEAD
//    @Transient
    public ArrayList<ArticleComment> getUserComments() {        return userComments;}

    public void setUserComments(ArrayList<ArticleComment> userComments) {this.userComments = userComments;}


=======
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
    public User(UserForm userForm){
        this.username = userForm.getUsername();
        this.userSex = userForm.getUserSex();
        this.userRole = userForm.getUserRole();
<<<<<<< HEAD
        this.password = userForm.getPassword();
        this.userLastComment = Timestamp.valueOf(LocalDateTime.now());
        this.userComments = null;
=======
        this.userPassword = userForm.getUserPassword();
        this.userLastComment = Timestamp.valueOf(LocalDateTime.now());
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
<<<<<<< HEAD
                ", username='" + username + '\'' +
                ", userSex=" + userSex +
                ", userRole=" + userRole +
                ", password='" + password + '\'' +
=======
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userRole=" + userRole +
                ", userPassword='" + userPassword + '\'' +
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
                ", userLastComment=" + userLastComment +
                '}';
    }

}
