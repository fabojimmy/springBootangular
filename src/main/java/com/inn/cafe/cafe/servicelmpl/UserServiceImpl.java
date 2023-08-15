package com.inn.cafe.cafe.servicelmpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.cafe.POJO.User;
import com.inn.cafe.cafe.constents.CafeConstants;
import com.inn.cafe.cafe.dao.UserDao;
import com.inn.cafe.cafe.service.UserService;
import com.inn.cafe.cafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
            log.info("Inside signup{}",requestMap);
        try{
            if(validateSignUpMap(requestMap)){
                    User user=userDao.findByEmailId(requestMap.get("email"));

                    if (Objects.isNull(user)) {
                         userDao.save(getUserFromMap(requestMap));
                         return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                    } else {
                        return CafeUtils.getResponseEntity("Email already exits",HttpStatus.BAD_REQUEST);
                    }
            }else{
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private boolean validateSignUpMap(Map<String,String> requestMap){

       if( requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
        && requestMap.containsKey("password") ){

            return true;
        }
            return false;
        
    }

    private User getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setRole(requestMap.get("user"));
        user.setStatus(requestMap.get("false"));
        return user;
    }
    
}
