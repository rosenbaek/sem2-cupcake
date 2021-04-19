package business.services;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;

public class UserFacade
{
    UserMapper userMapper;

    public UserFacade(Database database)
    {
        userMapper = new UserMapper(database);
    }

    public void addCreditToUserBalance(int userId, float amount) throws UserException {
        userMapper.addCreditToUserBalance(userId,amount);
    }

    public User login(String email, String password) throws UserException
    {
        return userMapper.login(email, password);
    }

    public User createUser(String email, String password) throws UserException
    {
        User user = new User(email, password, "customer", 0);
        userMapper.createUser(user);
        return user;
    }

    public void deductUserBalance(User user, float amount) throws UserException
    {
        userMapper.deductUserBalance(user,amount);
    }

}
