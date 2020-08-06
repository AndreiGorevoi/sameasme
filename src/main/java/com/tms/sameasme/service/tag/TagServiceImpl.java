package com.tms.sameasme.service.tag;

import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.tag.Tag;
import com.tms.sameasme.repository.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getTagByName(ETag tag) {
        return tagRepository.findTagByName(tag);
    }
}
