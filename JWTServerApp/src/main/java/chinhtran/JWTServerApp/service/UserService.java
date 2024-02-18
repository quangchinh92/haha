package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.entity.UserEntity;

public interface UserService {

    /**
     * Get user by username
     *
     * @param username
     * @return User
     */
    public UserEntity getUserByUsername(String username);

    /**
     * Get user by username
     *
     * @param username
     * @param password
     * @return User
     */
    public UserEntity getUserByUsernameAndPassword(String username, String password);
}
