package com.inn.cafe.cafe.restlmpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.cafe.constents.CafeConstants;
import com.inn.cafe.cafe.rest.UserRest;
import com.inn.cafe.cafe.service.UserService;
import com.inn.cafe.cafe.utils.CafeUtils;

@RestController
public class UserRestlmpl implements UserRest{

    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        
        try {
            
            return userService.signUp(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
