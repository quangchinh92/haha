package chinhtran.JWTServerApp.controller.role.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RolePostReq {
    private Long id;
    private String value;
    @JsonProperty("authorizationList")
    private List<AuthorizationModel> authorizationModelList;
}
