package com.stack.controller;

import com.stack.model.entities.User;
import com.stack.model.repo.UserRepository;
import com.stack.service.DatabaseService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


@Controller
public class UsersController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("user/login");

        return model;

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public ModelAndView registration() {

        ModelAndView model = new ModelAndView();
        model.setViewName("user/registration");

        return model;
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(value = "login") String login,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "email") String email) {

        User user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setLogin(login);
        user.setEmail(email);

        databaseService.save(user);

        return new ModelAndView("redirect:/");
    }

}
