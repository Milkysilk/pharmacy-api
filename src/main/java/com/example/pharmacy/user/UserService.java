package com.example.pharmacy.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.readByName(username);
    }

    @Secured("ROLE_ADMIN")
    public List<User> getAllUser() {
        return userMapper.readAll();
    }

    @Secured("ROLE_ADMIN")
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userMapper.create(user) == 1) {
            log.debug("新用户id：" + user.getId());
            if (userMapper.addRole(user.getId(), user.getRoles()) >= 1) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public User get(Integer id) {
        String name =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.read(id);
        if (!user.getUsername().equals(name)) {
            return null;
        }
        return userMapper.read(id);
    }

    public Boolean update(User user, Principal principal) {
        if (!user.getUsername().equals(principal.getName()) && !user.getUsername().equals("root")) {
            return false;
        }
//        不能停用超级用户呀
        if (user.getUsername().equals("root") && user.getEnable() == 0) {
            return false;
        }
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userMapper.update(user) > 0;
    }

    @Secured("ROLE_ADMIN")
    public Boolean delete(Integer id) {
//        不能删除超级用户呀
        if (id == 1) {
            return false;
        }
        return userMapper.delete(id) == 1;
    }
}
