package riccardomamoli.lesson_3.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardomamoli.lesson_3.entites.User;
import riccardomamoli.lesson_3.payloads.UserPayload;
import riccardomamoli.lesson_3.services.UserServive;

import java.util.List;
import java.util.UUID;

/* I METODI CRUD

1. GET http://localhost:3001/users
2. POST http://localhost:3001/users (+ req.body) --> 201
3. GET http://localhost:3001/users/{userId}
4. PUT http://localhost:3001/users/{userId} (+ req.body)
5. DELETE http://localhost:3001/users/{userId} --> 204

 */


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServive userServive;

    @GetMapping
    public List<User> findAll(){
        return this.userServive.findAll();
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody UserPayload body){
        return this.userServive.save(body);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId){
        return this.userServive.findById(userId);
    }

    @PutMapping("/{userId")
    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody UserPayload body){
        return this.userServive.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId){
        this.userServive.findByIdAndDelete(userId);
    };

}
