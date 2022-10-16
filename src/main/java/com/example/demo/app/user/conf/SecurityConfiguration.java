package com.example.demo.app.user.conf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.app.user.service.DatabaseUserDetailsService;

@Configuration
public class SecurityConfiguration {
	@Autowired
	private DatabaseUserDetailsService dudservice;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(dudservice);
	}
	
	@Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	

	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 throws Exception {
		 
	 http.formLogin(login -> login // フォーム認証の設定記述開始
		 .loginProcessingUrl("/login") // ユーザー名・パスワードの送信先URL
		 .loginPage("/login") // ログイン画面のURL
		 .defaultSuccessUrl("/kaninnyou") // ログイン成功後のリダイレクト先URL
		 .failureUrl("/login?error") // ログイン失敗後のリダイレクト先URL
		 .permitAll()
		 )
	 
	 	 .logout(logout -> logout // ログアウトの設定記述開始
		 .logoutSuccessUrl("/login") // ログアウト成功後のリダイレクト先URL
		 .invalidateHttpSession(true)
		 .deleteCookies("JSESSIONID")   
		 )
	 	 
	 	 .authorizeHttpRequests(authz -> authz // URLごとの認可設定記述開始
		 .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
		 .permitAll() // "/css/**"などはログイン無しでもアクセス可能
		 .mvcMatchers()
		 .permitAll() // "/css/**"などはログイン無しでもアクセス可能
		 .mvcMatchers("/singup")
		 .permitAll() // "/"はログイン無しでもアクセス可能
		 .anyRequest()
	 	 .authenticated()
	 );

	 return http.build();
	 }
}

