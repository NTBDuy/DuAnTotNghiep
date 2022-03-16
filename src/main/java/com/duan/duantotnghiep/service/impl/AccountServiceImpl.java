package com.duan.duantotnghiep.service.impl;

import com.duan.duantotnghiep.DTO.ChangePassDTO;
import com.duan.duantotnghiep.DTO.RegisterDTO;
import com.duan.duantotnghiep.entites.*;
import com.duan.duantotnghiep.repositories.AccountRepository;
import com.duan.duantotnghiep.service.AccountService;
import com.duan.duantotnghiep.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BCryptPasswordEncoder pe;
    @Autowired
    SendMailService sendMailService;
    Response response = new Response();

    private static Random generator = new Random();
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String specials = "~=+%^*/()[]{}/!@#$?|";
    private static final String ALL = alpha + alphaUpperCase + digits + specials;
    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

    @Override
    public List<Accounts> search(String keyword) {
        return accountRepository.findAllByUsernameLikeOrEmailLikeOrFullnameLikeOrPhoneLike(keyword,keyword,keyword,keyword);
    }

    @Override
    public Accounts findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Response changePass(ChangePassDTO changePassDTO) {
        Accounts a = accountRepository.findByUsername(changePassDTO.getUsername());
        if (!pe.matches(changePassDTO.getCurrent_pass(),a.getPassword())){
            System.out.println("Wrong password!");
            response.setMess("Wrong password!");
            return response;
        } else if (!changePassDTO.getNew_pass().equals(changePassDTO.getConfirm_pass())){
            System.out.println("Password does not matches!");
            response.setMess("Password does not matches!");
            return response;
        } else {
            a.setPassword(pe.encode(changePassDTO.getNew_pass()));
            accountRepository.save(a);
            System.out.println("Change password successful!");
            response.setMess("Change password successful!");
            return response;
        }
    }

    @Override
    public Response forgot(String mail) {
        if (!accountRepository.existsByEmail(mail)){
            System.out.println("Email does not exist!");
            response.setMess("Email does not exist!");
            return response;
        } else {
            Accounts acc = accountRepository.findByEmail(mail);
            String Pss = randomPassword();
            acc.setPassword(pe.encode(Pss));
            accountRepository.save(acc);
            try {
                sendMailForgot(acc, Pss, "Forgot Password!");
            } catch (Exception ex) {
                System.out.println("ERROR WITH SEND MAIL!");
                ex.printStackTrace();
            }
            System.out.println("We sent an email!");
            response.setMess("We sent an email, please check your inbox!");
            return response;
        }
    }

    @Override
    public Response create(Accounts a) {
        if (accountRepository.existsById(a.getUsername())){
            System.out.println("Username already exists!");
            response.setMess("Username already exists!");
            return response;
        } else if (accountRepository.existsByEmail(a.getEmail())){
            System.out.println("Email already exists!");
            response.setMess("Email already exists!");
            return response;
        } else {
            a.setPassword(pe.encode(a.getPassword()));
            Authorities au = new Authorities();
            au.setAccounts(a);
            Roles ro = new Roles();
            ro.setID("CUST");
            au.setRoles(ro);
            accountRepository.save(a);
            System.out.println("Create Success!");
            response.setMess("Create success!");
            return response;
        }
    }

    @Override
    public Response register(RegisterDTO registerDTO) {
        if (accountRepository.existsById(registerDTO.getUsername())){
            System.out.println("Username already exists!");
            response.setMess("Username already exists!");
            return response;
        } else if (accountRepository.existsByEmail(registerDTO.getEmail())){
            System.out.println("Email already exists!");
            response.setMess("Email already exists!");
            return response;
        } else {
            Accounts a = new Accounts();
            a.setEmail(registerDTO.getEmail());
            a.setFullname(registerDTO.getFullname());
            a.setPhone(registerDTO.getPhone());
            a.setUsername(registerDTO.getUsername());
            a.setPassword(pe.encode(registerDTO.getPassword()));
            a.setRegister_date(new Date());
            a.setStatus(false);
            Authorities au = new Authorities();
            au.setAccounts(a);
            Roles ro = new Roles();
            ro.setID("CUST");
            au.setRoles(ro);
            accountRepository.save(a);
            try {
                sendMailRegister(a, "REGISTER SUCCESSFULLY");
            } catch (Exception ex) {
                System.out.println("ERROR WITH SEND MAIL!");
                ex.printStackTrace();
            }
            System.out.println("Register Success!");
            response.setMess("Register success!");
            return response;
        }
    }

    @Override
    public List<Accounts> findAll() {
        return accountRepository.findAll();
    }


    @Override
    public Accounts update(Accounts a) {
        if (!this.BCRYPT_PATTERN.matcher(a.getPassword()).matches()) {
            a.setPassword(pe.encode(a.getPassword()));
        }
        return accountRepository.save(a);
    }

    @Override
    public void delete(String username) {
        accountRepository.deleteById(username);
    }

    // sendmail
    public void sendMailForgot(Accounts accounts, String otp, String notifycation) {
        System.out.println(accounts.getUsername());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h3>Hi " + accounts.getFullname() + "!</h3>\r\n");
        stringBuilder.append("<h5>Your new password: " + otp + " </h5>\r\n"+ "Please change your password soon.  \r\n"+  "    <h5>Have a nice day!</h5>");
        sendMailService.queue(accounts.getEmail(), notifycation, stringBuilder.toString());
    }
    // sendmail register
    public void sendMailRegister(Accounts accounts, String notifycation) {
        System.out.println(accounts.getUsername());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h3>Hi " + accounts.getFullname() + "!</h3>\r\n");
        stringBuilder.append("<h5>You have just successfully registered an account at the Store with Username: "+accounts.getUsername()+" </h5>\r\n"+"Sign in and enjoy shopping at: http://localhost:8888/ \r\n"+"    <h5>Have a nice day!</h5>");
        sendMailService.queue(accounts.getEmail(), notifycation, stringBuilder.toString());
    }

    public String randomPassword() {
        List<String> result = new ArrayList<>();
        Consumer<String> appendChar = s -> {
            int number = randomNumber(0, s.length() - 1);
            result.add("" + s.charAt(number));
        };
        appendChar.accept(digits);
        appendChar.accept(specials);
        while (result.size() < 10) {
            appendChar.accept(ALL);
        }
        Collections.shuffle(result, generator);
        return String.join("", result);
    }
    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

}
