package com.tms.sameasme.repository.post;

import com.tms.sameasme.model.ETag;
import com.tms.sameasme.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("from Post p where p.tag.name=?1")
    List<Post> findAllByTag(ETag tag);

    @Query("from Post p where p.id=?1")
    Post findPostById(Long id);

}
