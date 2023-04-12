package com.example.spcurrency;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.hibernate.cfg.AvailableSettings.USER;

@Controller
//@RequestMapping("/login")
public class MyController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private HistoryService historyService;

    private final PasswordEncoder passwordEncoder;


    public MyController(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    @GetMapping("/registered")
    public String registered(){
        return "/registered.html";
    }
    @GetMapping("/register")
    public String register(Model model){
//        UsersService user = new UsersService();
        String name = model.addAttribute("name").toString();
        String surname = model.addAttribute("surname").toString();
        String username = model.addAttribute("username").toString();
        String password = model.addAttribute("password").toString();
        UserRole role = UserRole.USER;
        usersService.addUser(name, surname, username, password, role);
        return "redirect:/registered";
//        if(user.addUser(name, surname, username, password, role)){
//            return "registered";
//        }
//        else{
//            return "login";
//        }

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // SpEL !!!
    public String admin(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "admin";
    }

    private boolean isAdmin(User user) {
        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority auth : roles) {
            if ("ROLE_ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }
    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
























//    public Users addUser(@RequestBody Users user) {
//        return usersService.addUser(user.getName(), user.getPassword(), user.getSurname(), user.getUsername());
//    }

//    @GetMapping("/users/{username}")
//    public Users getUserByUsername(@PathVariable String username) {
//        return usersService.getUserByUsername(username);
//    }
//
//    @PostMapping("/history")
//    public ResponseEntity<?> addHistory(@RequestBody History history) {
//        Users user = usersService.getUserByUsername(history.getUser().getUsername());
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//        historyService.addHistory(history.getFirst(), history.getSecond(), user);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/history")
//    public List<History> getAllHistory() {
//        return historyService.getAllHistory();
//    }
}
