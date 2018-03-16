package com.cx.controller;

import com.cx.bean.UserContans;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author CX
 * @create 2018/3/14
 */
@Controller
@RequestMapping("/sso")
public class SSOController {
    private static final Logger log = LoggerFactory.getLogger(SSOController.class);
    @GetMapping("/login")
    public String login(Model model,HttpServletRequest request) {
        log.info("GET login ....");
        String msg = request.getParameter("msg");
        String redirectUrl = request.getParameter("redirectUrl");
        if (StringUtils.isNotEmpty(msg)) {
            model.addAttribute("msg",msg);
        }
        if (StringUtils.isNotEmpty(redirectUrl)) {
            model.addAttribute("redirectUrl",redirectUrl);
        }
        return "sso/login";
    }
    @PostMapping("/login")
    public String login(Model model, HttpServletResponse rsp, HttpServletRequest req) throws UnsupportedEncodingException {
        log.info("POST login ...");
        String redirectUrl = req.getParameter("redirectUrl");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        model.addAttribute("redirectUrl",redirectUrl);
        String msg = null;
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            msg = "用户名或密码不能为空";
            model.addAttribute("msg",msg);
            return "redirect:/sso/login?msg=" + URLEncoder.encode(msg,"UTF-8");
        }
        if (!"cx".equals(username)) {
            msg = "该用户不存在";
            model.addAttribute("msg",msg);
            return "redirect:/sso/login?msg=" + URLEncoder.encode(msg,"UTF-8");
        }
        if ("cx".equals(username) && !"cx".equals(password)) {
            msg = "用户名或密码错误";
            model.addAttribute("msg",msg);
            return "redirect:/sso/login?msg="  + URLEncoder.encode(msg,"UTF-8");
        }
        String token = UUID.randomUUID().toString();
        UserContans userContans = UserContans.getInstance();
        userContans.setMaps(token,username);
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(60);
        cookie.setHttpOnly(true);
        cookie.setPath("localhost:8080");
        rsp.addCookie(cookie);
        if (StringUtils.isEmpty(redirectUrl)) {
            redirectUrl = "/";
        }
        log.info("redirectUrl : {}",redirectUrl);
        log.info("host : {}",req.getRemotePort());
        return "redirect:" + redirectUrl;
    }
}
