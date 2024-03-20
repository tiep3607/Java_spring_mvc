package vn.ltnc.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ltnc.laptopshop.domain.User;
import vn.ltnc.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("nvtiep2002@gmail.com");
        System.out.println(arrUsers);
        return "hello";
    }

    // @RequestMapping("/admin/user")
    // public String getUserPage(Model model) {
    // model.addAttribute("newUser", new User());
    // return "admin/user/create";
    // }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);

        return "admin/user/show";
    }

    // @RequestMapping("/admin")
    // public String getAdminPage(Model model) {
    // return "adminPage";
    // }

    @RequestMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hienthi) {
        System.out.println("result" + hienthi);
        this.userService.handleSaveUser(hienthi);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}") // GET
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping(("/admin/user/update"))
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User hienthi) {
        User currentUser = this.userService.getUserById(hienthi.getId());
        if (currentUser != null) {
            currentUser.setAddress(hienthi.getAddress());
            currentUser.setFullName(hienthi.getFullName());
            currentUser.setPhone(hienthi.getPhone());

            this.userService.handleSaveUser(hienthi);
        }

        return "redirect:/admin/user";
    }

}