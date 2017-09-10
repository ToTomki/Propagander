package pl.tomaszkubicz.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.tomaszkubicz.UserRepository;
import pl.tomaszkubicz.model.user.User;


import java.util.Arrays;

@Service
public class UserAuth implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


<<<<<<< HEAD
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("Drukuję loadUser 1, userName to: " + userName + ", tu po lewej powinien być userName. Pojawił się?");
        User user = userRepository.findByUserName(userName);
        System.out.println("Drukuję loadUser 2. Spróbujemy wydrukować user.toString: " + user.toString());
        if (user == null) {
            System.out.println("Jeśli user == null to to się wypisuje w konsoli. User to: " + user.toString());
            throw new UsernameNotFoundException("Nie znaleziono użytkownika");
        }
        System.out.println("Drukuję loadUser 3. Wyświetliło się to jeśli warunek w ifie się nie spełnił");
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());
        System.out.println("Drukuję loadUser 4");
        System.out.println(user.toString());
        org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), Arrays.asList(authority));
        UserDetails details = (UserDetails) springUser;
        System.out.println(springUser.toString());
=======
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) throw new UsernameNotFoundException("Nie znaleziono użytkownika");
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());
        org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), Arrays.asList(authority));
        UserDetails details = (UserDetails) springUser;

>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819
        return details;
    }
}