package br.com.sgm.iam.service;

import java.util.ArrayList;

import br.com.sgm.iam.model.UserModel;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = UserModel.builder().email("email").password("$2a$10$wP6Ax739xes3vNR5sAEVdewT.66xIZwF17qi8iuy5pAX2rO6q.DcS").build(); // TODO Acessar banco de dados

        // TODO:: ligar esse ponto a um banco de dados

        if (user.getEmail().equals(email)) {
            return new User(email, user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}