package app.dao;

import app.model.Role;
import app.model.User;
import io.javalin.validation.ValidationException;

public interface ISecurityDAO {
    User getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username, String password);
    Role createRole(String role);
    User addUserRole(String username, String role);
}
