package com.example.security.springsecurity.app.user.controller;

import com.example.security.springsecurity.app.user.model.UserVO;
import com.example.security.springsecurity.app.user.service.UserService;
import com.example.security.springsecurity.enums.UserRole;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Log4j2
public class UserController {

    private final UserService userService;

    @NotNull private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/loginView")
    public String loginView(){
        return "user/login";
    }

    /*
    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, RedirectAttributes redirectAttributes, @ModelAttribute UserVO userVO){
        log.error("@@@");
        String userPw = userVO.getUserPw();
        userVO = userService.findUserByUserEmail(userVO.getUserEmail());
        if(userVO == null || !passwordEncoder.matches(userPw, userVO.getUserPw())){
            redirectAttributes.addFlashAttribute("rsMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/user/loginView";
        }
        request.getSession().setAttribute("userVO", userVO);
        return "redirect:/index";
    }
    */

    @GetMapping("/init")
    public String createAdmin(@ModelAttribute UserVO userVO){
        userVO.setUserEmail("user@naver.com");
        userVO.setUserPw(passwordEncoder.encode("test"));
        userVO.setRole(UserRole.USER);
        if(userService.createUser(userVO) == null){
            log.error("Create Admin Error");
        }

        userVO.setUserEmail("admin@naver.com");
        userVO.setUserPw(passwordEncoder.encode("test"));
        userVO.setRole(UserRole.ADMIN);
        if(userService.createUser(userVO) == null){
            log.error("Create Admin Error");
        }
        return "redirect:/index";
    }
}
