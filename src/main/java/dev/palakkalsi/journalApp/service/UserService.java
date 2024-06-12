package dev.palakkalsi.journalApp.service;

import dev.palakkalsi.journalApp.entity.JournalEntry;
import dev.palakkalsi.journalApp.entity.User;
import dev.palakkalsi.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        try {
            userRepository.save(user);
        }catch (Exception e) {
            log.error("Exception ", e);
        }
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById (ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
