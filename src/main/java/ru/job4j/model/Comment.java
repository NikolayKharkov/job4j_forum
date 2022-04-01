package ru.job4j.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "Post_ID_FK"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "User_ID_FK"))
    private User user;

    @CreationTimestamp
    private Calendar created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String commentText) {
        this.text = commentText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Comment{"
                + "id=" + id
                + ", commentText='" + text + '\''
                + ", user=" + user
                + ", created=" + created
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return id == comment.id
                && Objects.equals(text, comment.text)
                && Objects.equals(user, comment.user)
                && Objects.equals(created, comment.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, user, created);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
