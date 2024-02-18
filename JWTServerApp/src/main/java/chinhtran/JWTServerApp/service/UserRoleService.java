package chinhtran.JWTServerApp.service;

import java.util.List;

public interface UserRoleService {
    public void updateRole(Long userId, List<Long> roleIdList);
}
