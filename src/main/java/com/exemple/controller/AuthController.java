package com.exemple.controller;

import com.exemple.entity.User;
import com.exemple.service.InterfaceUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@SessionAttributes({"error"})
public class AuthController {

    private InterfaceUserService interfaceUserService;

    @RequestMapping("/")
    public String login(SessionStatus status)
    {
        status.setComplete();
        return "auth/login";
    }

    @GetMapping("/dashboard")
    public String dashboard()
    {
        return "/dashboard";
    }

    @PostMapping("/login")
    public String auth(@RequestParam String email, @RequestParam String password, Model model, HttpSession session)
    {
        if(interfaceUserService.authenticate(email, password) != null)
        {
            User user = interfaceUserService.authenticate(email, password);
            session.setAttribute("name", user.getName());
            return "redirect:/dashboard";
        }else{
            model.addAttribute("error", "Fail login with this email & password try again !");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        return "redirect:/";
    }

}
