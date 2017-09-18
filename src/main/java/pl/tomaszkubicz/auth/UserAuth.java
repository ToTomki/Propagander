package pl.tomaszkubicz.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.tomaszkubicz.dao.UserRepository;
import pl.tomaszkubicz.model.user.User;


import java.util.Arrays;

@Service
public class UserAuth implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());
        org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
        UserDetails details = (UserDetails) springUser;
        return details;
    }
}