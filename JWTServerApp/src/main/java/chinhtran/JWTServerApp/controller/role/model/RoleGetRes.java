package chinhtran.JWTServerApp.controller.role.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoleGetRes {
    private Long id;
    private String value;
    @JsonProperty("authorizationList")
    private List<AuthorizationModel> authorizationModelList;
}
