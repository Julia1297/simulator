package application.controllers;
import application.exception.UsernameAlreadyExistsException;
import application.models.User;
import application.services.UserService;
import com.cloudant.client.api.CloudantClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.MalformedURLException;

@RestController
public class UserController {

    private CloudantClient client;


    @Autowired
    private UserService userService;


    @PostMapping(value="/postulant",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPostulant( @Valid @RequestBody String newUser,
                                 HttpServletResponse response    ) throws MalformedURLException, UsernameAlreadyExistsException {
        if(newUser!=null){
            Gson gson=new Gson();
            User user=gson.fromJson(newUser,User.class);
            user.setRole("Postulant");
            userService.save(user);
            response.setStatus(HttpStatus.CREATED.value());

        }
    }
    @PostMapping(value="/director",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDirector(@Valid @RequestBody String newUser,
                                HttpServletResponse response) throws MalformedURLException, UsernameAlreadyExistsException {
        if(newUser!=null){
            Gson gson=new Gson();
            User user=gson.fromJson(newUser,User.class);
            user.setRole("Director");
            userService.save(user);
            response.setStatus(HttpStatus.CREATED.value());
        }
    }

}
