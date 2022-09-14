package ru.kovalchuk.user.dao;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kovalchuk.user.model.AddUserRequest;
import ru.kovalchuk.user.model.Role;
import ru.kovalchuk.user.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Setter
@Component
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(AddUserRequest userRs) {
        User userFromDB = userRepository.findByUsername(userRs.getNameUser());

        if (userFromDB != null) {
            return false;
        }

        User user = new User();
        user.setUsername(userRs.getNameUser());
        // Collections.singleton - т.к. роли - массив, подразумевается что у пользователя может быть несколько ролей
        user.setRoles(Collections.singleton(roleRepository.findByName("KOT")));
        user.setPassword(bCryptPasswordEncoder.encode(userRs.getPassUser()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}