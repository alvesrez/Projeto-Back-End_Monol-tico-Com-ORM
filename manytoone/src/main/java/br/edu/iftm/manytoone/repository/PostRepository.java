package br.edu.iftm.manytoone.repository;

import org.springframework.data.repository.CrudRepository;
import br.edu.iftm.manytoone.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
    
}
