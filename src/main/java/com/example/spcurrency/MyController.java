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
@Controller
public class MyController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private HistoryService historyService;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;


    public MyController(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "redirect:/login";
    }
//    @GetMapping("/login")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
//    @PostMapping("/registered")
//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String registered(Model model){
//        return "register";
//    }
    @RequestMapping(value = "/logined", method = RequestMethod.GET)
    public String logined(){
        return "logined";
    }
    @RequestMapping("/registerSuccessfully")
    public String viewRegisterSuccessful(Model model) {
        return "registeredSuccesfully";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegisterPage(Model model){
        return "registerPage";

    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname", required = false) String surname,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam(value = "password", required = false) String password){
        // Create a new user
        Users user = new Users();
        UserRole role = UserRole.USER;
        String passHash = passwordEncoder.encode("password");
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(passHash);
        user.setRole(role);

        // Save the user to the database
        usersRepository.save(user);
        return "redirect:/registerSuccessfully";
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
