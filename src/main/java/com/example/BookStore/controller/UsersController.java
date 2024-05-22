package com.example.BookStore.controller;


import com.example.BookStore.models.Users;
import com.example.BookStore.service.UsersService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/users")
    public String getUsers(Model model, @Param("keyWord1") String keyWord1, @Param("keyWord2") String keyWord2,@Param("keyWord3") String keyWord3,@Param("keyWord4") String keyWord4,@Param("keyWord5") String keyWord5) {

        model.addAttribute("listaUsuarios",usersService.getUsers(keyWord1,keyWord2,keyWord3, keyWord4, keyWord5));
        model.addAttribute("keyWord1",keyWord1);
        model.addAttribute("keyWord2",keyWord2);
        model.addAttribute("keyWord3",keyWord3);
        model.addAttribute("keyWord4",keyWord4);
        model.addAttribute("keyWord5",keyWord5);

        return "users";
    }

    @RequestMapping("/newUser")
    public String showFormNewUser(Model model){
        Users user = new Users();
        model.addAttribute("Users", user);
        return "new_user";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("Users") Users users){
        users.setRoleId(3);
        usersService.save(users);
        return "redirect:/users";
    }

    @RequestMapping("/editUser/{id}")
    public ModelAndView showFormUpdateUser(@PathVariable(name="id") Long id){
        ModelAndView model = new ModelAndView("edit_user");
        Users user = usersService.findUsersById(id);
        model.addObject("Users", user);
        return model;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String UpdateUser(@ModelAttribute("Users") Users users){
        users.setRoleId(3);
        usersService.updateUsers(users.getId(),users);
        return "redirect:/users";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deletUser(@PathVariable(name="id") Long id){
        usersService.deleteUsers(id);
        return "redirect:/users";
    }
}
