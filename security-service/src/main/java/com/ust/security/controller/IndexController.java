package com.ust.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexController {


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public String index() {
        return "Hello world";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "Hello User";
    }
}
