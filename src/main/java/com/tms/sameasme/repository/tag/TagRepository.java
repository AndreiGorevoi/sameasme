package com.tms.sameasme.repository.tag;

import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("from Tag t where t.name=?1")
    Tag findTagByName(ETag tag);

}
