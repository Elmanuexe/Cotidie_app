package salesianos.triana.dam.cotidie.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import salesianos.triana.dam.cotidie.security.jwt.Autenticacion;
import salesianos.triana.dam.cotidie.security.jwt.Autorizacion;
import salesianos.triana.dam.cotidie.security.jwt.FiltroSeguridad;
import salesianos.triana.dam.cotidie.usuario.model.Role;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final Autenticacion autenticacion;
    private final Autorizacion autorizacion;

    private final PasswordEncoder codificador;
    private final UserDetailsService userDetailsService;

    private final FiltroSeguridad filtroSeguridad;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(codificador);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(autorizacion)
                .accessDeniedHandler(autenticacion)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/h2-console").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/turno/**").authenticated()
                .antMatchers(HttpMethod.GET,"/ausencia/**").authenticated()
                .antMatchers(HttpMethod.GET,"/plan/**").authenticated()
                .antMatchers(HttpMethod.POST,"/ausencia/**").authenticated()
                .anyRequest().permitAll();

        http.addFilterBefore(filtroSeguridad, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();


    }
}
