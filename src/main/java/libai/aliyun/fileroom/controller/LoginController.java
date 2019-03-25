package libai.aliyun.fileroom.controller;

import libai.aliyun.fileroom.dao.UserDao;
import libai.aliyun.fileroom.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/login")
    public String getLoginForm(){
        return "login";
    }


    @PostMapping(value = "/login")
    public String login(@ModelAttribute(name = "loginForm")UserEntity loginForm, Model model,HttpSession session) {
        String userName = loginForm.getUsername();
        String password = loginForm.getPassword();
        if(userDao.getUser(userName,password) != null){
            session.setAttribute("user",loginForm);
            return "redirect:/upfile";
        }

        model.addAttribute("invalidCredentials", true);
        return "login";
    }


}
