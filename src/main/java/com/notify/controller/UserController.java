package com.notify.controller;

import com.notify.entity.ExecOwnershipEntity;
import com.notify.entity.LargeHoldingsEntity;
import com.notify.entity.UserInfo;
import com.notify.repository.UserRepository;
import com.notify.service.ExecOwnershipService;
import com.notify.service.LargeHoldingsService;
import com.notify.service.webclient.WebClientLargeHoldingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;
    private LargeHoldingsService largeHoldingsService;
    private ExecOwnershipService execOwnershipService;

    @GetMapping("/")
    public void getUsers() {

        userRepository.findAll();
        UserInfo userInfo = UserInfo.builder()
                .id(1245)
                .userEmail("to9251@naver.com")
                .userPwd("123123").build();

        userRepository.save(userInfo);
//        return userRepository.findAll();
    }

    @GetMapping("/get")
    public List<LargeHoldingsEntity> get() {
        return null;
    }

    @GetMapping("/test")
    public void getTest() {
        largeHoldingsService.insertData();
    }

    @GetMapping("/test1")
    public List<ExecOwnershipEntity> getTest1() {
        return execOwnershipService.insertData();
    }


}
