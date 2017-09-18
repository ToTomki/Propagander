package pl.tomaszkubicz;

<<<<<<< HEAD
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;
=======
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
        import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
<<<<<<< HEAD
        import org.springframework.security.core.userdetails.UserDetailsService;
        import pl.tomaszkubicz.auth.UserAuth;
        import pl.tomaszkubicz.dao.ArticleRepository;
        import pl.tomaszkubicz.dao.UserRepository;


@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {

=======
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import pl.tomaszkubicz.auth.UserAuth;
        import pl.tomaszkubicz.model.user.User;

        import java.util.List;
        import java.util.logging.Logger;


@Configuration
//@ComponentScan(basePackages = { "org.baeldung.security" })
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {


<<<<<<< HEAD
=======
    @Autowired
    LoginHandler loginHandler;
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819

>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserAuth userAuth;

    @Autowired
    ArticleRepository articleRepository;

<<<<<<< HEAD
    @Autowired
    UserRepository userRepository;

=======
<<<<<<< HEAD
    @Autowired
    UserRepository userRepository;

=======
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
<<<<<<< HEAD
                    .antMatchers("/article/{articleFile}", "/logout").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR", "USER")
                    .antMatchers("/user/addUser", "/", "/user/{userData}", "/login", "/403").permitAll()
                    .antMatchers("/article/add").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR")
=======
<<<<<<< HEAD
                    .antMatchers("/article/{articleFile}", "/logout").hasAnyRole("ROLE_ADMIN", "ROLE_CHIEF", "ROLE_REDACTOR", "ROLE_USER")
                    .antMatchers("/user/addUser", "/", "/user/{userData}", "/login", "/403").permitAll()
                    .antMatchers("/article/add").hasAnyRole("ROLE_ADMIN", "ROLE_CHIEF", "ROLE_REDACTOR")
=======
                    .antMatchers("/article/{articleFile}", "/logout").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR", "USER")
                    .antMatchers("/user/addUser", "/", "/user/{userData}", "/login", "/403").permitAll()
                    .antMatchers("/article/add").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR")
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    //.successHandler(loginHandler)
                    .loginPage("/login")
                    .failureUrl("/failureLogin")
                    .successForwardUrl("/article/add")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll();

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc
        System.out.println("configureGlobal 1");
        ShaPasswordEncoder encoder  = new ShaPasswordEncoder();
        auth.userDetailsService(userAuth).passwordEncoder(encoder);
        System.out.println("configureGlobal 2" + userAuth.toString() + auth.toString());
<<<<<<< HEAD
    }
=======
=======
        ShaPasswordEncoder encoder  = new ShaPasswordEncoder();
        auth.userDetailsService(userAuth).passwordEncoder(encoder);
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819
    }
//
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(encoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//    }
>>>>>>> 87983a5965a805ff3fadbf39929d56802a9936fc


}
