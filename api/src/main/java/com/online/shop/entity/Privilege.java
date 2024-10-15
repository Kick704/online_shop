package com.online.shop.entity;

import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Класс-сущность привилегий для роли
 */
@Entity
@Table(name = "privileges")
public class Privilege extends AbstractEntity {

    /**
     * Название привилегии
     */
    @Column(name = "name")
    private String name;

    /**
     * Набор ролей с данной привилегией
     */
    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;

    public Privilege() {
    }

    private Privilege(Builder builder) {
        name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                Objects.equals(name, privilege.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class Builder {
        private String name;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Privilege build() {
            if (name == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Privilege: поле name не может быть null"
                );
            }
            return new Privilege(this);
        }
    }
}
