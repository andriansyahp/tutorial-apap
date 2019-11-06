package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.service.RoleService;
import apap.tutorial.gopud.service.UserService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        boolean passwordIsValid = userService.checkPasswordValidity(user.getPassword());
        model.addAttribute("listRole", roleService.findAll());
        if (!passwordIsValid){
            model.addAttribute("invalidPass", "Password is invalid");
            return "home";
        }
        model.addAttribute("invalidPass", null);
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    private String updatePasswordFormPage(
            @RequestParam("old") String oldPassword,
            @RequestParam("new") String newPassword,
            @RequestParam("confirmation") String confirmPassword,
            Model model
    ){
        UserModel user = userService.getUserByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );
        boolean passwordIsCorrect = userService.checkPasswordAgainstSavedDatabase(user, oldPassword);
        boolean passwordIsUnchanged = userService.checkPasswordAgainstSavedDatabase(user, newPassword);
        boolean newPasswordIsValid = userService.checkPasswordValidity(newPassword);
        boolean mismatchedNewPassword = userService.checkNewPasswordAgaintConfirmationPassword(newPassword, confirmPassword);
        if (!passwordIsCorrect){
            model.addAttribute("error", "Old password is not correct");
            return "update-password";
        } if (passwordIsUnchanged){
            model.addAttribute("error", "New password is the same as the old one");
            return "update-password";
        } if (!newPasswordIsValid){
            model.addAttribute("error", "New password does not follow the rules for inclusion of number and letter with more than 8 characters");
            return "update-password";
        } if (mismatchedNewPassword){
            model.addAttribute("error", "Confirmed password and New password is different");
            return "update-password";
        }
        userService.updatePassword(user, newPassword);
        return "redirect:/";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    private String updatePasswordFormPage(@ModelAttribute UserModel user) {
        return "update-password";
    }
}
