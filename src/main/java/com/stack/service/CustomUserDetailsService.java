package com.stack.service;

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

  //  private final AdminRepository userRepository;

    @Autowired
    public CustomUserDetailsService() {
 //       this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByLogin(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
//        }
        return new UserRepositoryUserDetails();
    }

    private final static class UserRepositoryUserDetails implements UserDetails {

        private static final long serialVersionUID = 1L;

        private UserRepositoryUserDetails() {
       //     super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return ""; // getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }

    }

}
