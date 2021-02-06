package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/*
设置用户信息明文传输
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}