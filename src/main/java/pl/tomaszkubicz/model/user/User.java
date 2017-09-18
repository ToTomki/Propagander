package pl.tomaszkubicz.model.user;

import pl.tomaszkubicz.model.article.ArticleComment;
import pl.tomaszkubicz.model.user.enums.UserRole;
import pl.tomaszkubicz.model.user.enums.UserSex;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String password;
    @Column(name="last_comment")
    private Timestamp userLastComment;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @Transient
    private ArrayList<ArticleComment> userComments;

    public User(Long userId, String username, UserSex userSex, UserRole userRole, String password, Timestamp userLastComment, ArrayList<ArticleComment> userComments) {
        this.userId = userId;
        this.username = username;
        this.userSex = userSex;
        this.userRole = userRole;
        this.password = password;
        this.userLastComment = userLastComment;
        this.userComments = userComments;
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

//    @Transient
    public ArrayList<ArticleComment> getUserComments() {        return userComments;}

    public void setUserComments(ArrayList<ArticleComment> userComments) {this.userComments = userComments;}


    public User(UserForm userForm){
        this.username = userForm.getUsername();
        this.userSex = userForm.getUserSex();
        this.userRole = userForm.getUserRole();
        this.password = userForm.getPassword();
        this.userLastComment = Timestamp.valueOf(LocalDateTime.now());
        this.userComments = null;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userSex=" + userSex +
                ", userRole=" + userRole +
                ", password='" + password + '\'' +
                ", userLastComment=" + userLastComment +
                '}';
    }

}
