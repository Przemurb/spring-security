//package com.example.springsecurity.security.service;
//
//import com.example.springsecurity.security.domain.User;
//import com.example.springsecurity.security.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//
//public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
//
//    @Autowired
//    private  UserRepository userRepository;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) {
//        final User user = userRepository.findByEmail(authentication.getName());
//        if (user == null) {
//            throw new BadCredentialsException("Niepoprawny login lub hasło");
//        }
//
//        final Authentication authenticate = super.authenticate(authentication);
//        return new UsernamePasswordAuthenticationToken(user,
//                                                        authenticate.getCredentials(),
//                                                        authenticate.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
