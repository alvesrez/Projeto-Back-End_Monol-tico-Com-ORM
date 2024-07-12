package br.edu.iftm.manytoone.repository;

import org.springframework.data.repository.CrudRepository;
import br.edu.iftm.manytoone.domain.PostComment;

public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
    
}
