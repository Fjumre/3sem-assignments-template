package app.dao;

import app.model.Role;
import java.util.Set;

public interface IUser extends ISecurityUser  {

    boolean verifyPassword(String password);
    Set<String> getRolesAsStrings();
    void addRole(Role userRole);

    void setUsername(String username);
    void setUserPassword(String userPassword);

    String getUsername();
    Set<Role> getRoleList();
}