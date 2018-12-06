package application.services;


import application.exception.UserNotFoundException;
import application.exception.UsernameAlreadyExistsException;
import application.models.User;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;

@Service
public class UserService {



    @Autowired
    private Database db;


    public User getUser(String username) throws UserNotFoundException {

        QueryResult<User> queryUser = db.query(new QueryBuilder(eq("username", username)).build(), User.class);
        List<User> userRegistered =  queryUser.getDocs();
        if(userRegistered.size()==0)
            throw new UserNotFoundException(username);
        else
            return userRegistered.get(0);
    }


    public void save(User user) throws  UsernameAlreadyExistsException {

        QueryResult<User> queryUser = db.query(new QueryBuilder(eq("username", user.getUsername())).build(), User.class);
        List<User> userRegistered =  queryUser.getDocs();
        if(userRegistered.size()==0) {
            db.save(user);
        }
        else
            throw new UsernameAlreadyExistsException(user.getUsername());

    }


}
