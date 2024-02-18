package chinhtran.JWTServerApp.service;

import java.util.List;

import chinhtran.JWTServerApp.controller.role.models.RoleGetRes;
import chinhtran.JWTServerApp.controller.role.models.RolePostReq;
import chinhtran.JWTServerApp.controller.role.models.RolePostRes;

public interface RoleService {

    public RoleGetRes getByPK(Long roleId);

    public List<RoleGetRes> getAll();

    public RolePostRes upsert(RolePostReq rolePostReq);

    public void deleteByPK(Long roleId);
}
