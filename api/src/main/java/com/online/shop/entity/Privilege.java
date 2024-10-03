package com.online.shop.entity;

import com.online.shop.enums.PrivilegeEnum;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "privileges")
public class Privilege extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "privilege_name")
    private PrivilegeEnum privilegeName;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;

    public Privilege() {
    }

    private Privilege(Builder builder) {
        privilegeName = builder.privilegeName;
    }

    public PrivilegeEnum getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(PrivilegeEnum privilegeName) {
        this.privilegeName = privilegeName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Privilege privilege)) return false;
        return Objects.equals(id, privilege.id) &&
                Objects.equals(privilegeName, privilege.privilegeName) &&
                Objects.equals(roles, privilege.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, privilegeName, roles);
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", privilegeName='" + privilegeName + '\'' +
                '}';
    }

    public static final class Builder {
        private PrivilegeEnum privilegeName;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder privilegeName(PrivilegeEnum val) {
            privilegeName = val;
            return this;
        }

        public Privilege build() {
            if (privilegeName == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Privilege: поле privilegeName ссылается на null"
                );
            }
            return new Privilege(this);
        }
    }
}
