package com.stack.service;

import com.stack.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserRepositoryUserDetails(userService.findByLogin(username));
    }

    private final static class UserRepositoryUserDetails extends AbstractUserDetails {

        private static final long serialVersionUID = 1L;
        private User user;

        private UserRepositoryUserDetails(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public String getPassword() {
            if(user != null){
                return user.getPassword();
            }
            return null;
        }

        @Override
        public String getUsername() {
            if(user != null){
                return user.getLogin();
            }
            return null;
        }
    }

}
