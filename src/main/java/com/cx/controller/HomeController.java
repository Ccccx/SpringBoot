package com.cx.controller;

import com.cx.bean.UserContans;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CX
 * @create 2018/1/12
 */
@Controller
public class HomeController {
    private Logger log = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/")
    public String index(HttpServletRequest req,HttpServletResponse rsp) {
        log.info(" index ...");
        return "index";

    }
    @GetMapping("/have")
    public String have(HttpServletRequest req, Model model) {
        log.info("have ... ");
        req.getSession().setAttribute("what","有铅笔");
        log.info("SessionId:" + req.getSession().getId());
        model.addAttribute("sessionId",req.getSession().getId());
        return "have";

    }
    @GetMapping("/how")
    public String how(HttpServletRequest req, Model model) {
        log.info("have ... ");
        req.getSession().setAttribute("how","￥1");
        log.info("SessionId:" + req.getSession().getId());
        model.addAttribute("sessionId",req.getSession().getId());
        return "how";
    }
    @GetMapping("/path1/getCookies")
    public String getCookies(HttpServletRequest req, HttpServletResponse rsp) {
        log.info("getCookies ... ");
        Cookie[] cookies = req.getCookies();
        getCookies(cookies);
        Cookie cookie = new Cookie("myCookie",System.currentTimeMillis() + "");
        cookie.setHttpOnly(false);//JS不能读取和处理
        cookie.setMaxAge(24*60*60);//24小时
        cookie.setSecure(false);//true标识只支持HTTPS协议
        //cookie.setPath("/");
        cookie.setDomain("www.baidu.com");
        rsp.addCookie(cookie);
        return "index";
    }

    @GetMapping("/path2/getCookies")
    public String getCookies2(HttpServletRequest req, HttpServletResponse rsp) {
        log.info("getCookies ... ");
        Cookie[] cookies = req.getCookies();
        getCookies(cookies);
        return "index";
    }

    private void getCookies(Cookie[] cookies) {
        if (cookies != null) {
            System.out.println("---------------------------------------------------------------");
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " | "
                        + cookie.getValue() + " | "
                        + cookie.getMaxAge() + " | "
                        + cookie.getPath() + " | "
                        + cookie.getDomain() + " | "
                        + cookie.getSecure() + " | "
                        + cookie.isHttpOnly());
            }
            System.out.println("---------------------------------------------------------------");
        }
    }
}
