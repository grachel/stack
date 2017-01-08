package com.stack.service;

import com.stack.model.dao.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserRepositoryUserDetails(User.findById(username));
    }

    private final static class UserRepositoryUserDetails implements UserDetails {

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
            return "admin";
        }

        @Override
        public String getUsername() {
            return user.getDisplayName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

}
