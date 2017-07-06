package pl.tomaszkubicz.model;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @Column(columnDefinition = "UNSIGNED INT(10)") // przypisanie do pola w bazie MySQL wartości unsigned oznacza, że pole nie przyjmuje wartości ujemnych, dzięki czemu obsłużyć można więcej liczb dodatnich
    @GeneratedValue // automatycznie generuje unikatową wartość dla tego pola
    private int idUser;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(String userName) {
        this.userName = userName;
    }


}
