package com.ssafy.vue.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.MemberUser;
import com.ssafy.vue.exception.ResourceNotFoundException;
import com.ssafy.vue.help.UserIdentityAvailability;
import com.ssafy.vue.help.UserProfile;
import com.ssafy.vue.repository.UserRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
    	logger.debug("getUserProfile - 호출");
		logger.debug("" + username);
        MemberUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        
        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
        return userProfile;
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserProfile userProfile, @PathVariable(value = "username") String username) {
    	logger.debug("updateUserInfo - 호출");
		logger.debug("" + userProfile);
		
		try {
			MemberUser prev = userRepository.findByUsername(username)
					.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
			
			prev.setName(userProfile.getName());
			MemberUser result = userRepository.save(prev);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		
    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable(value = "id") long id) {
    	logger.debug("deleteUserInfo - 호출");
		logger.debug("" + id);
		
//		try {
			userRepository.deleteById(id);
//		}catch (Exception e) {
//			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//		}
		
    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}
