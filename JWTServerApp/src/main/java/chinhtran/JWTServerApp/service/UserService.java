package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.entity.User;

public interface UserService {

    /**
     * Get user by username
     *
     * @param username
     * @return User
     */
    public User getUserByUsername(String username);

    /**
     * Get user by username
     *
     * @param username
     * @param password
     * @return User
     */
    public User getUserByUsernameAndPassword(String username, String password);
}
