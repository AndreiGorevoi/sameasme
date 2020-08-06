package com.tms.sameasme.service.tag;

import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.tag.Tag;

public interface TagService {
    Tag getTagByName(ETag tag);
}
