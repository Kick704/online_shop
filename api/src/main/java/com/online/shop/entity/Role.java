package com.online.shop.entity;

import com.online.shop.enums.RoleEnum;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleEnum roleName;

    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<Privilege> privileges;

    public Role() {
    }

    private Role(Builder builder) {
        roleName = builder.roleName;
        privileges = builder.privileges;
    }

    public RoleEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public static final class Builder {
        private RoleEnum roleName;
        private Set<Privilege> privileges;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder roleName(RoleEnum val) {
            roleName = val;
            return this;
        }

        public Builder privileges(Set<Privilege> val) {
            privileges = val;
            return this;
        }

        public Role build() {
            if (roleName == null || CollectionUtils.isEmpty(privileges)) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Role: одно или несколько полей (roleName, privileges) пустые или ссылаются на null"
                );
            }
            return new Role(this);
        }
    }
}
