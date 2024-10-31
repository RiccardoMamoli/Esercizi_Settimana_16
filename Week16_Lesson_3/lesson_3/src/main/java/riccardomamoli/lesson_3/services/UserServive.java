package riccardomamoli.lesson_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import riccardomamoli.lesson_3.entites.User;
import riccardomamoli.lesson_3.exceptions.BadRequestException;
import riccardomamoli.lesson_3.exceptions.NotFoundException;
import riccardomamoli.lesson_3.payloads.UserPayload;
import riccardomamoli.lesson_3.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserServive {

    @Autowired
    private UserRepository userRepository;

    public User save(UserPayload body){
        this.userRepository.findByEmail(body.getEmail()).ifPresent(user -> {throw new BadRequestException("Email " + body.getEmail() + " gia in uso.");});
        User newUser = new User(body.getName(), body.getSurname(), body.getEmail(), body.getPassword(), "https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());
        return this.userRepository.save(newUser);
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User findById(UUID userId){
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByIdAndUpdate(UUID userId, UserPayload body){
        User found = this.findById(userId);
        if(!found.getEmail().equals(body.getEmail())){
            this.userRepository.findByEmail(body.getEmail()).ifPresent(user -> {throw new BadRequestException("Email gia in uso");});
        }
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        found.setAvatarURL("https://ui-avatar-com/api/?name=" + body.getName() + "+" + body.getSurname());
        return this.userRepository.save(found);
    }

    public void findByIdAndDelete(UUID userId){
        User found = this.findById(userId);
        this.userRepository.delete(found);
    }
}
