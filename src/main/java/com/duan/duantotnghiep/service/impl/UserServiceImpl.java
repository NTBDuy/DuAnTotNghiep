package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.entites.Accounts;
import com.duan.duantotnghiep.entites.Authorities;
import com.duan.duantotnghiep.entites.Roles;
import com.duan.duantotnghiep.repositories.AccountRepository;
import com.duan.duantotnghiep.repositories.AuthorityRepository;
import com.duan.duantotnghiep.repositories.RoleRepository;
import com.duan.duantotnghiep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    BCryptPasswordEncoder pe;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        try {
            Accounts account = accountRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
            String []roles = account.getAuthorities().stream()
                    .map(au -> au.getRoles().getID())
                    .collect(Collectors.toList()).toArray(new String[0]);
            return User.withUsername(account.getUsername())
                    .password(account.getPassword())
                    .roles(roles).build();
        } catch (Exception e) {
            throw new UsernameNotFoundException(usernameOrEmail +" not found");
        }
    }

    @Override
    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
        String email = oauth2.getPrincipal().getAttribute("email");
        String[]parts = email.split("@");
        String userN = parts[0];
        String pass = pe.encode("123@abcxyzacas");
        System.out.println(email);
        if(accountRepository.existsById(email)==false) {
            Accounts ac = new Accounts();
            ac.setEmail(email);
            ac.setUsername(userN);
//            ac.setPhone(oauth2.getPrincipal().getAttribute("phone"));
            ac.setFullname(oauth2.getPrincipal().getAttribute("name"));
            ac.setPassword(pass);
            accountRepository.save(ac);
            Authorities au = new Authorities();
            au.setAccounts(ac);
            Roles ro = new Roles();
            ro.setID("CUST");
            au.setRoles(ro);
            authorityRepository.save(au);
            System.out.println("Vừa tạo tk thành công!");
            UserDetails user = User.withUsername(userN)
                    .password(pass).roles("CUST").build();
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            Accounts account = accountRepository.findById(email).get();
            String[]roles = account.getAuthorities().stream()
                    .map(au -> au.getRoles().getID())
                    .collect(Collectors.toList()).toArray(new String[0]);
            UserDetails user = User.withUsername(userN)
                    .password(pass).roles(roles).build();
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
}
