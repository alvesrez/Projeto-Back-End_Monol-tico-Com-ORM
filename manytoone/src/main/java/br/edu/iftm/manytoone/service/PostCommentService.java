package br.edu.iftm.manytoone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.manytoone.domain.PostComment;
import br.edu.iftm.manytoone.repository.PostCommentRepository;

@Service
public class PostCommentService {

    @Autowired
    private PostCommentRepository repository;

    public List<PostComment> getAll(Long postId) {
        try {
            List<PostComment> postComments = new ArrayList<>();
            repository.findAll().forEach(postComment -> {
                if (postComment.getPost().getId().equals(postId)) {
                    postComments.add(postComment);
                }
            });
            return postComments;
        } catch (Exception e) {
            return null;
        }
    }

    public PostComment getById(Long postId, Long id) {
        Optional<PostComment> postComment = repository.findById(id);
        return postComment.filter(comment -> comment.getPost().getId().equals(postId)).orElse(null);
    }

    public PostComment create(Long postId, PostComment postComment) {
        postComment.getPost().setId(postId);
        return repository.save(postComment);
    }

    public PostComment update(Long postId, Long id, PostComment postComment) {
        Optional<PostComment> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            PostComment existingPostComment = existingItemOptional.get();
            if (!existingPostComment.getPost().getId().equals(postId)) {
                return null;
            }
            existingPostComment.setReview(postComment.getReview());
            return repository.save(existingPostComment);
        } else {
            return null;
        }
    }

    public boolean delete(Long postId, Long id) {
        try {
            Optional<PostComment> postComment = repository.findById(id);
            if (postComment.isPresent() && postComment.get().getPost().getId().equals(postId)) {
                repository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
