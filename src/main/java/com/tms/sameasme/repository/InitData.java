package com.tms.sameasme.repository;

import com.tms.sameasme.model.ETag;
import com.tms.sameasme.model.Tag;
import com.tms.sameasme.model.User;
import com.tms.sameasme.repository.post.PostRepository;
import com.tms.sameasme.repository.tag.TagRepository;
import com.tms.sameasme.repository.user.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class InitData implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if(applicationEvent instanceof ContextRefreshedEvent){
            ApplicationContext context = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            PostRepository postRepository = context.getBean(PostRepository.class);
            TagRepository tagRepository = context.getBean(TagRepository.class);
            UserRepository userRepository = context.getBean(UserRepository.class);
            User user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            user.setName("admin");
            userRepository.save(user);

            Tag tag1 = new Tag();
            Tag tag2 = new Tag();
            Tag tag3 = new Tag();
            Tag tag4 = new Tag();
            tag1.setName(ETag.FOOTBALL);
            tag2.setName(ETag.BASKETBALL);
            tag3.setName(ETag.VOLLEYBALL);
            tag4.setName(ETag.TENNIS);

            tagRepository.save(tag1);
            tagRepository.save(tag2);
            tagRepository.save(tag3);
            tagRepository.save(tag4);



        }
    }
}
