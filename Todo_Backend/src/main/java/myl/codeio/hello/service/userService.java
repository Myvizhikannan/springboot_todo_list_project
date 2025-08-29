package myl.codeio.hello.service;
import myl.codeio.hello.model.user;
import myl.codeio.hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service

public class userService {
    @Autowired
    private UserRepository userRepositeries;
    public user createuser(user User){
        return userRepositeries.save(User);
    }
    public user getUserById(long id){
        return userRepositeries.findById(id).orElseThrow(()->new RuntimeException("ToDo not found"));
    }
}
