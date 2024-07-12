package br.edu.iftm.manytoone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post")
    private List<PostComment> comments = new ArrayList<>();

    public void addComment(PostComment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
}
