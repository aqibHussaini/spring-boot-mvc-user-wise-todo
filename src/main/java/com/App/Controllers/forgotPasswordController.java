package com.App.Controllers;

import com.App.Entity.User;
import com.App.repository.userRepository;
import com.App.service.sendingEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequestMapping("/forgot-password")
public class forgotPasswordController {
    @Autowired
    userRepository userRepository;
    @Autowired
    sendingEmail sendingEmail;
    @GetMapping("/email-form")
    public String emailForm()
    {
        return "email-form.html";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String password, Model model, HttpSession httpSession)
    {
        User user=this.userRepository.findByEmail(httpSession.getAttribute("email").toString());
        user.setPassword(password);
        this.userRepository.save(user);
        return "redirect:/login";
    }
    @PostMapping("/verify-otp")
    public String verfiyOtp(@RequestParam int otp, Model model,HttpSession httpSession)
    {
        String page=null,msg=null;
        int number=Integer.parseInt(""+httpSession.getAttribute("otp"));
        if(otp==number)
        {
            page="change-forgot-password.html";
        }
        else
        {
            page="verify-otp.html";
            msg="Invalid OTP";
        }
        model.addAttribute("msg",msg);
        return page;
    }
    @PostMapping("/get-email")
    public String emailForm(@RequestParam String email, Model model, HttpSession httpSession)
    {
        String message= null;
        String returning_page=null;
        try
        {
          User user=  this.userRepository.findByEmail(email);
            Random rnd = new Random();
            int number = rnd.nextInt(999999);
        this.sendingEmail.sendEmail(email,""+number);
        httpSession.setAttribute("otp",number);
        httpSession.setAttribute("email",user.getEmail());
            System.out.println(httpSession.getAttribute("email").toString());
            returning_page  ="verify-otp.html";
        }
        catch (Exception e)
        {
         model.addAttribute("msg","invaid email "+e.getMessage());
          returning_page  ="email-form.html";
        }
        return returning_page;
    }
}
