package org.lahssini.demojpa.service;

import org.lahssini.demojpa.entities.Role;
import org.lahssini.demojpa.entities.User;

public interface UserService  {
   User addNewUser(User user);
   Role addNewRole(Role role);
   User findUserByUserName(String userName);
   Role findRoleByRoleName(String roleName);
   void addRoleToUser(String userName,String rolName);
   User authenticate(String userName,String password);


}
