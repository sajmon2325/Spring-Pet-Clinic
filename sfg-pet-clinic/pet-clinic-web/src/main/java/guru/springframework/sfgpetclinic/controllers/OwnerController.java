package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//This is another way of mapping
//with this @RequestMapping annotation we are setting a prefix that every requestMapping here will start with /owners prefix
@RequestMapping("/owners")
@Controller
public class OwnerController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOfOwners(){

        return "owners/index";
    }
}
