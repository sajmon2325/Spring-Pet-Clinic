package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//This is another way of mapping
//with this @RequestMapping annotation we are setting a prefix that every requestMapping here will start with /owners prefix
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    //Spring will automatically wire a mode bcs we have specified the Mode mode inside ()
    public String listOfOwners(Model model){

        //Spring MVC will  run this method, this method calls findAll() method from ownerServices
        //The map that will be created is stored under name "owners" som the View can find this map
        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(){
        return "notimplemented";
    }

    //There are two overloaded addObject() methods in ModelAndVirew. One is instinctive - addObject(String attributeName, Object attributeValue)
    //
    //The other one addObject(Object attributeValue) is what John used. If you look at the official documentation: Add an attribute to the model using parameter name generation.
    //
    //So Spring will auto generate the attribute name (owner), based on the value (Owner).
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }


}
