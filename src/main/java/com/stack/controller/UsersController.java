package com.stack.controller;

import com.stack.model.DomainContext;
import com.stack.model.dao.User;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UsersController {

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

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public ModelAndView registration() {

        ModelAndView model = new ModelAndView();
        model.setViewName("user/registration");

        return model;
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(value = "login") String login,
                                     @RequestParam(value = "displayname") String displayname,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "email") String email) {

        Session session = null;
        try {
            session = DomainContext.openSession();
            User user = new User(session);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setLogin(login);
            user.setDisplayName(displayname);
            user.setEmail(email);
            user.save();
            session.flush();
        }
        finally {
            DomainContext.closeSession(session);
        }

        return new ModelAndView("redirect:/");
    }

}
