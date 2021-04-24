package com.App.Controllers;

import com.App.Entity.User;
import com.App.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    userRepository userRepository;

    @GetMapping("/change-password")
    public String changePasswordForm()
    {
        return "change-password.html";
    }

    @RequestMapping(value = "/save-password",method = RequestMethod.POST)
    public String savePassword(@RequestParam String Password,@RequestParam String newPassword,Model model)
    {
        String msg=null;
        try
        {
            User user=this.userRepository.findByPassword(Password);
            user.setPassword(newPassword);
            this.userRepository.save(user);
            msg="password changed succesfully!!!";
        }
        catch (Exception e)
        {
            msg="OOOPPPs!!! User 404"+e.getMessage();
        }
        model.addAttribute("msg",msg);
        return "change-password.html";
    }

    @GetMapping("/dashboad")
    public String dashboad(Principal principal)
    {
        User user = this.userRepository.findByName(principal.getName());

        return "redirect:/todos/get-todos/"+user.getId();
    }
    @GetMapping("/register")
    public String registerForm(Model model)
    {
        model.addAttribute("user",null);
        return "register.html";
    }
    @PostMapping("/save-user")
    public String save(User user,Model model)
    {
        user.setCreated_at(new Date());
       this.userRepository.save(user);
        model.addAttribute("user",user.getName()+" has been registered successfully");
        return "register.html";
    }
}
