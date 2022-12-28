package com.application.security;

import com.application.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(@Qualifier("userDAOImpl") IUserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserDetailsImpl(userDAO.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found")));
    }
}
