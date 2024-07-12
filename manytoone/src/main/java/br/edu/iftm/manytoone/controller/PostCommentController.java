package br.edu.iftm.manytoone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.manytoone.domain.PostComment;
import br.edu.iftm.manytoone.service.PostCommentService;

@RestController
@RequestMapping("/post/{postId}/comment")
class PostCommentController {

    @Autowired
    PostCommentService service;

    @GetMapping
    public List<PostComment> getAll(@PathVariable("postId") Long postId) {
        return service.getAll(postId);
    }

    @GetMapping("{commentId}")
    public PostComment getById(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return service.getById(postId, commentId);
    }

    @PostMapping
    public PostComment create(@PathVariable("postId") Long postId, @RequestBody PostComment item) {
        return service.create(postId, item);
    }

    @PutMapping("{commentId}")
    public PostComment update(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody PostComment item) {
        return service.update(postId, commentId, item);
    }

    @DeleteMapping("/{commentId}")
    public boolean delete(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return service.delete(postId, commentId);
    }
}
