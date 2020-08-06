package com.tms.sameasme.config.security.provider;

import com.tms.sameasme.model.user.AppUser;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.findUserByLogin(authentication.getName());
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }

        String password = authentication.getCredentials().toString();
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        List<SimpleGrantedAuthority> authorityList = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName().toString()))
                .collect(Collectors.toList());
        AppUser appUser = AppUser.fillAppUser(user);
        return new UsernamePasswordAuthenticationToken(appUser,null,authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
