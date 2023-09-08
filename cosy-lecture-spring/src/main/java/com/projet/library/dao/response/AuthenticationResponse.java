package com.projet.library.dao.response;


//import com.authentication.sprinjwt.entities.Role2;
//import com.authentication.sprinjwt.entities.Role;
//import com.authentication.sprinjwt.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projet.library.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private Integer id;
    private String email;
    private Role role;

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;

}