package org.lahssini.demojpa.service;
import lombok.AllArgsConstructor;
import org.lahssini.demojpa.entities.Role;
import org.lahssini.demojpa.entities.User;
import org.lahssini.demojpa.repository.RoleRepository;
import org.lahssini.demojpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService  {
    private  UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String rolname) {
        User user=findUserByUserName(username);
        Role role=findRoleByRoleName(rolname);
        if(user.getRoles()!= null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

    }

    @Override
    public User authenticate(String userName, String password) {
        User user=userRepository.findByUsername(userName);
        if(user.getPassword().equals(password)){
            return user;}
        throw new RuntimeException("Bad credentials");

    }
}
