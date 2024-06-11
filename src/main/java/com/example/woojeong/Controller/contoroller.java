package com.example.woojeong.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class contoroller {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/index-detail")
    public String indexDetail(){
        return "index-detail";
    }

}
