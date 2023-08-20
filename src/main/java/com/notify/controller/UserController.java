package com.notify.controller;

import com.notify.entity.LargeHoldings;
import com.notify.entity.UserInfo;
import com.notify.repository.LargeHoldingsRepository;
import com.notify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LargeHoldingsRepository largeHoldingsRepository;

    @GetMapping("/")
    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get")
    public List<LargeHoldings> get() {
        return largeHoldingsRepository.findAll();
    }


}
