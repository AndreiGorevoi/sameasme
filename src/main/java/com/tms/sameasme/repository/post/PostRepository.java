package com.tms.sameasme.repository.post;

import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.tag.ETag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("from Post p where p.tag.name=?1 and p.active=true")
    List<Post> findAllByTag(ETag tag);

    @Query("from Post p where p.id=?1 and p.active=true")
    Post findPostById(Long id);

    @Query("select p from Post p where p.active=true order by p.matchDate desc")
    List<Post> findAllOrderByMatchDate();

    @Query("select p from Post p where p.active=true order by p.createDate desc")
    List<Post> findAllOrderByCreateDate();

    void deleteById(Long id);

    @Query("from Post p where p.matchDate>=?1 and p.matchDate<=?2 and p.active=true")
    List<Post> findAllByMatchTime(Date startPeriod, Date endPeriod);

    @Query("from Post p where p.tag.name=?1 and p.matchDate>=?2 and p.matchDate<=?3 and p.active=true")
    List<Post> findPostsByMatchTimeAndTag(ETag tag,Date startPeriod, Date endPeriod);

    @Query("from Post p where p.active=true and p.user.id=?1")
    List<Post> findPostsByUserId(Long id);

}
