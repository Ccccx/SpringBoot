package com.cx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CX
 * @create 2018/1/12
 */
@Controller
@RequestMapping("/index")
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
