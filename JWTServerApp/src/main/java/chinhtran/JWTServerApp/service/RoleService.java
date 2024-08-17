package chinhtran.JWTServerApp.service;

import java.util.List;

import chinhtran.JWTServerApp.controller.role.model.RoleGetRes;
import chinhtran.JWTServerApp.controller.role.model.RolePostReq;
import chinhtran.JWTServerApp.controller.role.model.RolePostRes;

public interface RoleService {

    public RoleGetRes getByPK(Long roleId);

    public List<RoleGetRes> getAll();

    public RolePostRes upsert(RolePostReq rolePostReq);

    public void deleteByPK(Long roleId);
}
