package pl.tomaszkubicz.model;

import javax.persistence.*;

@Entity
@Table(name = "Comments") //I applied @Table annotation because the CamelCase shouldn't be used in MySQL
public class ArticleComment {

    @Id
    @Column(columnDefinition = "INT(13) UNSIGNED")
    @GeneratedValue
    int commentId;

    @Column (name = "Anonymous")
    boolean commentAnonymous;

    @Column (name = "Author")
    String commentAuthor;

    @Column (name = "Date")
    String commentData;

    @Column (name = "Content")
    String articleContent;

    @Column (name = "Assignation")
    String assignation;
}
