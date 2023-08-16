package com.devsh.devsh.repositories;

import com.devsh.devsh.entities.User;
import com.devsh.devsh.infra.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    UserDetails findByLogin(String username);

    @Query(nativeQuery = true, value = """
            SELECT tb_user.login AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority 
            FROM tb_user
            INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
            INNER JOIN tb_role ON tb_user_role.role_id = tb_role.id
            WHERE tb_user.login = :login
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String login);
}
