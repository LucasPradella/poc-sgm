package br.com.sgm.iam.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.sgm.iam.model.MockerUser;
import br.com.sgm.iam.model.Profiles;
import br.com.sgm.iam.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private MockerUser mockerUser;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = mockerUser.process(email);
        return new User(email, user.getPassword(), new ArrayList<>());
    }

}