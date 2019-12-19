package com.mitrais.smartclinic.controller;

import com.mitrais.smartclinic.model.ClinicUser;
import com.mitrais.smartclinic.model.Roles;
import com.mitrais.smartclinic.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

   private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String patient(Model model){
        List<ClinicUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/user";
    }

    @GetMapping("/add")
    public String showUserForm(Model model) {
        ClinicUser user = new ClinicUser();
        model.addAttribute("user",user);

        return "users/form-add";
    }

    @PostMapping("/add")
    public String saveUser(@Valid ClinicUser user) {
        user.setActive(1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model){
        ClinicUser user = userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "users/form-edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, ClinicUser user){
        user.setActive(1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}