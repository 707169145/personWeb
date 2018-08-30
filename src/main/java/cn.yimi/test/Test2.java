package cn.yimi.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/")
public class Test2 {

    @RequestMapping(value="/test")
    @ResponseBody
    public String t() {
        return "123";
    }
}
