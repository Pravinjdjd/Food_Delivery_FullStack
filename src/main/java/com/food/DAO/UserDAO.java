package com.food.DAO;

import java.util.List;

import com.food.Model.User;

public interface UserDAO {

    int addUser(User user);

    User getUser(int userId);

    User getUserByEmail(String email);

    void updateUser(User user);
   
    boolean updatePassword(int userId, String password);
    
    

    void deleteUser(int userId);

    List<User> getAllUser();

	
    

}