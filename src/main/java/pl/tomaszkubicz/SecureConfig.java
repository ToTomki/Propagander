package pl.tomaszkubicz;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.builders.WebSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
                    .antMatchers("/logout").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR", "USER")
                    .antMatchers("/", "/article/articleList", "/login", "/user/addUser", "user/editors", "/403", "/loggedin", "/outlogged", "/contact", "/article/{articleFile}").permitAll()
                    .antMatchers("/article/add").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR")
                    .antMatchers("/administration/account").authenticated()
                    .antMatchers("/administration/controlPanel", "/administration/findUser").hasAuthority("ADMIN")
                    .antMatchers("/resources/**", "/css/**").permitAll()
                    .antMatchers("/user/addUser").anonymous()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/loggedin")
                    .failureUrl("/failureLogin")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/outlogged")
                    .permitAll()
                .and().csrf().disable();

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
        System.out.println("configureGlobal 1");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userAuth).passwordEncoder(encoder);
        System.out.println("configureGlobal 2" + userAuth.toString() + auth.toString());
    }




}
