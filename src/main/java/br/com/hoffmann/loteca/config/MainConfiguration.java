package br.com.hoffmann.loteca.config;

import br.com.hoffmann.loteca.security.ImplementsUserDetailsService;
import br.com.hoffmann.loteca.security.JWTAuthenticationFilter;
import br.com.hoffmann.loteca.security.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MainConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN = "/v1/loteca/login";
    private static final String CADASTRA_USUARIO = "/v1/usuario/cadastraUsuario";
    private static final String DELETE_USUARIO = "/v1/usuario/deletaUsuario/*";
    private static final String UPDATE_USUARIO = "/v1/usuario/updateUsuario/*";
    private static final String BUSCAR_USUARIO = "/v1/usuario/buscaUsuarios";
    private static final String BUSCAR_USUARIOS = "/v1/usuario/buscaUsuario/*";

    private static final String CADASTRA_APOSTA = "/v1/loteca/cadastraAposta";
    private static final String DELETE_APOSTA = "/v1/loteca/deletaAposta/*";
    private static final String UPDATE_APOSTA = "/v1/loteca/updateAposta/*";
    private static final String BUSCAR_APOSTAS = "/v1/loteca/buscaApostas";
    private static final String BUSCAR_APOSTA_JOGADOR = "/v1/loteca/buscaApostasPorJogardor/*";
    private static final String BUSCAR_APOSTA_ID = "/v1/loteca/buscaApostasPorID/*";
    private static final String TESTE = "/v1/teste/ganhador";


    @Autowired
    private ImplementsUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, LOGIN).permitAll()
                .antMatchers(HttpMethod.POST, CADASTRA_USUARIO).permitAll()
                .antMatchers(HttpMethod.DELETE, DELETE_USUARIO).permitAll()
                .antMatchers(HttpMethod.PUT, UPDATE_USUARIO).permitAll()
                .antMatchers(HttpMethod.GET, BUSCAR_USUARIO).permitAll()
                .antMatchers(HttpMethod.GET, BUSCAR_USUARIOS).permitAll()

                .antMatchers(HttpMethod.POST, CADASTRA_APOSTA).permitAll()
                .antMatchers(HttpMethod.DELETE, DELETE_APOSTA).permitAll()
                .antMatchers(HttpMethod.PUT, UPDATE_APOSTA).permitAll()
                .antMatchers(HttpMethod.GET, BUSCAR_APOSTAS).permitAll()
                .antMatchers(HttpMethod.GET, BUSCAR_APOSTA_JOGADOR).permitAll()
                .antMatchers(HttpMethod.GET, BUSCAR_APOSTA_ID).permitAll()

                .antMatchers(HttpMethod.POST, TESTE).permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter(LOGIN, authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}