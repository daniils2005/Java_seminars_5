package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//TODO nomaint, lai lietotajus nem no datubazes
	@Bean
	public UserDetailsManager createTestUsers() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails userD1 = User.builder().username("daniils").password(encoder.encode("123")).authorities("USER").build(); //seciba nav svariga
		UserDetails userD2 = User.builder().username("janis").password(encoder.encode("321")).authorities("USER").build();
		UserDetails userD3 = User.builder().username("admin").password(encoder.encode("987")).authorities("ADMIN").build();
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(userD1, userD2, userD3);
		return manager;
	}
	
	@Bean
	public SecurityFilterChain configureEndpoints(HttpSecurity http) {
		
		//-> nozime ka ta ir lambdas izteiksme
		http.authorizeHttpRequests(
								auth->auth
								.requestMatchers("/produt/crud/all").permitAll()
								.requestMatchers("/product/crud/one?**").permitAll()
								.requestMatchers("/product/crud/all/**").permitAll()
								.requestMatchers("/product/crud/add").hasAuthority("ADMIN")
								.requestMatchers("/product/crud/update/**").hasAnyAuthority("Admin", "USER")
								.requestMatchers("/product/crud/delete/**").hasAuthority("ADMIN")
								.requestMatchers("/product/filter/price/**").permitAll()
								.requestMatchers("/h2-console/**").permitAll()
								//TODO uztaisit ari parejam kontrolieru funkcijam
								);
		
		http.formLogin(auth->auth.permitAll());
		
		//TODO prieks H2
		http.headers(auth->auth.frameOptions(op->op.disable()));
		http.csrf(auth->auth.ignoringRequestMatchers("/h2-console/**"));
		
		return http.build();
	
	}
}
