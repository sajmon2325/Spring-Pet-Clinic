package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //We marked this class as a controller. After we will write in the browser the url that matches the patterns
    //in @RequestMapping method the method executes. The method will look at a template called index based on return
    //statement, and display the index page
    @RequestMapping({"", "/", "index", "index.html"})
    public String index(){
        return "index";
    }
}
