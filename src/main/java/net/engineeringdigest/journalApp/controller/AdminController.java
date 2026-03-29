package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users != null && !users.isEmpty()) {
            users.forEach(u -> u.setPassword(null));
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<String> createAdminUser(@RequestBody User user) {
        userService.saveAdmin(user);
        return new ResponseEntity<>("Admin created", HttpStatus.CREATED);
    }

    @GetMapping("/refresh-app-cache")
    public ResponseEntity<String> refreshAppCache() {
        appCache.init();
        return new ResponseEntity<>("Cache refreshed", HttpStatus.OK);
    }
}
