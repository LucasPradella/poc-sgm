package br.com.sgm.iam.controller;

import br.com.sgm.iam.config.JwtTokenUtil;
import br.com.sgm.iam.model.Login;
import br.com.sgm.iam.model.LoginResponse;
import br.com.sgm.iam.model.User;
import br.com.sgm.iam.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import br.com.sgm.iam.api.SgmIamApi;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@RestController
@CrossOrigin
public class JwtAuthenticationController implements SgmIamApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @Override
    public ResponseEntity<LoginResponse> authenticate(Login authenticationRequest) {

        try {
            auth(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (Exception e){
            ResponseEntity.status(UNAUTHORIZED).build();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setType("Bearer");

        return  ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<Void> register(List<String> type, User body) {
        // TODO :: implementar cadastro de um novo usuario
        return null;
    }






    private void auth(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}