package org.jieun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // URL 분기를 담당한다
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }// resources\templates\index.html

    @GetMapping("/str")
    @ResponseBody
    public String test(){
        return "안녕하세요ㅎㅎ ";
    }
}
