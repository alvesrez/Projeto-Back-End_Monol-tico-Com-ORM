package br.edu.iftm.manytoone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.manytoone.domain.Post;
import br.edu.iftm.manytoone.domain.PostComment;
import br.edu.iftm.manytoone.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getAll() {
        try {
            List<Post> posts = new ArrayList<>();
            repository.findAll().forEach(posts::add);
            return posts;
        } catch (Exception e) {
            return null;
        }
    }

    public Post getById(Long id) {
        Optional<Post> post = repository.findById(id);
        return post.orElse(null);
    }

    public Post create(Post post) {
        return repository.save(post);
    }

    public Post update(Long id, Post post) {
        Optional<Post> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            Post existingItem = existingItemOptional.get();
            existingItem.setTitle(post.getTitle());
            return repository.save(existingItem);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método para adicionar um comentário a um post
    public void addComment(Long postId, PostComment comment) {
        Optional<Post> postOptional = repository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.addComment(comment);
            repository.save(post);
        }
    }

    // Método para remover um comentário de um post
    public void removeComment(Long postId, Long commentId) {
        Optional<Post> postOptional = repository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.getComments().removeIf(comment -> comment.getId().equals(commentId));
            repository.save(post);
        }
    }
}
