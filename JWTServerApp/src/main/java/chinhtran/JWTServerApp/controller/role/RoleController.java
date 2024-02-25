package chinhtran.JWTServerApp.controller.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinhtran.JWTServerApp.controller.role.models.RoleGetRes;
import chinhtran.JWTServerApp.controller.role.models.RolePostReq;
import chinhtran.JWTServerApp.controller.role.models.RolePostRes;
import chinhtran.JWTServerApp.service.RoleService;

@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RolePostRes> post(@RequestBody RolePostReq rolePostReq) {
        RolePostRes rolePostRes = roleService.upsert(rolePostReq);
        return ResponseEntity.ok(rolePostRes);
    }

    @GetMapping
    public ResponseEntity<List<RoleGetRes>> get() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping(path = "/{roleId}")
    public ResponseEntity<RoleGetRes> getById(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(roleService.getByPK(roleId));
    }

    @DeleteMapping(path = "/{roleId}")
    public ResponseEntity<Void> deleteById(@PathVariable("roleId") Long roleId) {
        roleService.deleteByPK(roleId);
        return ResponseEntity.ok().build();
    }
}
