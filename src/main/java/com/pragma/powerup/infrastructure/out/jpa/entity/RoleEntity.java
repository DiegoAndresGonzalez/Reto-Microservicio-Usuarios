package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "rol")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity implements GrantedAuthority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "rol")
    private String role;

    @Column(name = "descripcion")
    private String description;

    @Override
    public String getAuthority() {
        return "ROLE_" + role;
    }
}