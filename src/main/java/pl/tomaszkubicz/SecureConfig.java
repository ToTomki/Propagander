package pl.tomaszkubicz;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import pl.tomaszkubicz.auth.UserAuth;
        import pl.tomaszkubicz.dao.ArticleRepository;
        import pl.tomaszkubicz.dao.UserRepository;


@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserAuth userAuth;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/article/{articleFile}", "/logout").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR", "USER")
                    .antMatchers("/user/addUser", "/", "/user/{userData}", "/login", "/403").permitAll()
                    .antMatchers("/article/add").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR")
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
        System.out.println("configureGlobal 1");
        ShaPasswordEncoder encoder  = new ShaPasswordEncoder();
        auth.userDetailsService(userAuth).passwordEncoder(encoder);
        System.out.println("configureGlobal 2" + userAuth.toString() + auth.toString());
    }


}