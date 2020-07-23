package com.tms.sameasme.repository;

import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.role.Role;
import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.tag.Tag;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.repository.post.PostRepository;
import com.tms.sameasme.repository.role.RoleRepository;
import com.tms.sameasme.repository.tag.TagRepository;
import com.tms.sameasme.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@Repository
@Transactional
public class InitData implements ApplicationListener {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitData(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if(applicationEvent instanceof ContextRefreshedEvent){
            ApplicationContext context = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            PostRepository postRepository = context.getBean(PostRepository.class);
            TagRepository tagRepository = context.getBean(TagRepository.class);
            UserRepository userRepository = context.getBean(UserRepository.class);
            RoleRepository roleRepository = context.getBean(RoleRepository.class);

//          Set basic roles
            Role roleAdmin = new Role();
            Role roleUser = new Role();
            Role roleModerator = new Role();
            roleAdmin.setName(ERole.ADMIN);
            roleUser.setName(ERole.USER);
            roleModerator.setName(ERole.MODERATOR);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            roleRepository.save(roleModerator);

//            Set default user
            User user = new User();
            user.setLogin("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setName("admin");
            user.setRoles(Arrays.asList(roleAdmin,roleUser));

            User user2 = new User();
            user2.setLogin("user");
            user2.setPassword(passwordEncoder.encode("user"));
            user2.setName("user");
            user2.setRoles(Arrays.asList(roleUser));

            userRepository.saveAll(Arrays.asList(user,user2));

            //          Set basic tags
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

//            Set test post
            Post post = new Post();
            post.setCreateDate(new Date());
            post.setDescription("We are finding player for football match!");
            post.setImg("http://images-on-off.com/images/51/kaknazivayutitalyanskixlyubiteleyfutbola-64aaa4af.jpg");
            post.setMatchDate(new Date());
            post.setTitle("Football match!");
            post.setContactNumber("+375447475477");
            post.setLocation("Минск ул.Гамарника д.28");
            post.setPrice(5);
            post.setShowerPresent(true);
            post.setAmountOfPeople(5);
            post.setTag(tagRepository.findTagByName(ETag.FOOTBALL));
            post.setUser(userRepository.findUserByLogin("admin"));
            postRepository.save(post);

        }
    }
}
