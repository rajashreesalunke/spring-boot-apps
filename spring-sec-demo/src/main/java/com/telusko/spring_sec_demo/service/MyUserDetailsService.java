package com.telusko.spring_sec_demo.service;


import com.telusko.spring_sec_demo.dao.UserRepo;
import com.telusko.spring_sec_demo.model.User;
import com.telusko.spring_sec_demo.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     User user  = repo.findByUsername(username);
        if(user==null){
            System.out.println("User 404");
            throw new  UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);// so that we can access the user principal values in here myuserservice,it is accepted by the constructor of the userprincipal

    }
}
