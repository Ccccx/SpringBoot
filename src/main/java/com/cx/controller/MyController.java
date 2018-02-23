package com.cx.controller;

import com.cx.exception.MyException;
import com.cx.bean.MyRestResponse;
import com.cx.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author CX
 * @create 2018/1/10
 */
@RestController
public class MyController {

    private static Logger log = LoggerFactory.getLogger(MyController.class);

    @Value("${application.message:Hello springBoot}")
    private String message ;

    @GetMapping("/json")
    public User json() {
        User user = new User();
        user.setId(1);
        user.setName("cx");
        user.setAge(10);
        user.setSex("男");
        user.setRemark(message);
        return user;
    }

    @RequestMapping("/fail")
    public String fail() {
        throw new MyException("Oh dear!");
    }

    @RequestMapping("/fail2")
    public String fail2() {
        throw new IllegalStateException();
    }

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    MyRestResponse handleMyRuntimeException(MyException exception) {
        return new MyRestResponse("Some data I want to send back to the client.");
    }

}
