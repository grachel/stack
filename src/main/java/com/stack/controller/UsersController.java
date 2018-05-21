package com.stack.controller;

import com.stack.model.entities.User;
import com.stack.model.repo.user.UserRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@CrossOrigin(origins = {"http://localhost:3000"})
@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public @ResponseBody
    String registration(@RequestParam(value = "login") String login,
                 @RequestParam(value = "password") String password,
                 @RequestParam(value = "email") String email) {
        String error = "";
        try {
            User user = new User();
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setLogin(login);
            user.setEmail(email);

            userRepository.save(user);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return error;
    }

}
