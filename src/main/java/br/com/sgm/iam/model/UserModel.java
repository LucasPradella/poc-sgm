package br.com.sgm.iam.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {

    private String id;
    private String name;
    private String email;
    private String password;
    private String username;


}
