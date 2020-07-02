package com.tms.sameasme.repository.post;

import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.tag.ETag;
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

    @Query("select p from Post p order by p.matchDate desc")
    List<Post> findAllOrOrderByMatchDate();

    @Query("delete from Post p where p.id=?1")
    void deleteById(Long id);

}
