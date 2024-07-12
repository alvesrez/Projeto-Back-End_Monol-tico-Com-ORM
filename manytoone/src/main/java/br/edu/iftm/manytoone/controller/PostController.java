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

import br.edu.iftm.manytoone.domain.Post;
import br.edu.iftm.manytoone.domain.PostComment;
import br.edu.iftm.manytoone.service.PostService;

@RestController
@RequestMapping("/post")
class PostController {

    @Autowired
    PostService service;

    @GetMapping
    public List<Post> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return service.create(post);
    }

    @PutMapping("{id}")
    public Post update(@PathVariable("id") Long id, @RequestBody Post item) {
        return service.update(id, item);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @PostMapping("/{id}/comment")
    public void addComment(@PathVariable("id") Long postId, @RequestBody PostComment comment) {
        service.addComment(postId, comment);
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public void removeComment(@PathVariable("id") Long postId, @PathVariable("commentId") Long commentId) {
        service.removeComment(postId, commentId);
    }
}
