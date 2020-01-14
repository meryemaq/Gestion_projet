package com.example.demo.config.security.userdetails;

import com.example.demo.entities.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GuestService guestService;

    @Autowired
    private Converter<User, UserDetails> userUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = guestService.signIn(username);
        if (user.equals(null)) {
            throw new UsernameNotFoundException("No such user with username :" + username);
        }
        return userUserDetailsConverter.convert(user);
    }
}
