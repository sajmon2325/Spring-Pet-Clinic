package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//This is another way of mapping
//with this @RequestMapping annotation we are setting a prefix that every requestMapping here will start with /owners prefix
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //We don't want to allow the web forms to address and manipulate the ID property
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"", "/", "/index", "/index.html"})
//    //Spring will automatically wire a mode bcs we have specified the Mode mode inside ()
//    public String listOfOwners(Model model){
//
//        //Spring MVC will  run this method, this method calls findAll() method from ownerServices
//        //The map that will be created is stored under name "owners" som the View can find this map
//        model.addAttribute("owners", ownerService.findAll());
//
//        return "owners/index";
//    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping()
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }

        // find owners by last Name
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()){
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }else if (results.size() == 1){
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
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
