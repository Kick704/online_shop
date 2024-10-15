package com.online.shop.entity;

import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Класс-сущность роли пользователя интернет-магазина
 */
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    /**
     * Название роли
     */
    @Column(name = "name")
    private String name;

    /**
     * Набор пользователей с данной ролью
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     * Набор привилегий у данной роли
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<Privilege> privileges;

    public Role() {
    }

    private Role(Builder builder) {
        name = builder.name;
        privileges = builder.privileges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", privileges=" + privileges +
                '}';
    }

    public static final class Builder {
        private String name;
        private Set<Privilege> privileges;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder privileges(Set<Privilege> val) {
            privileges = val;
            return this;
        }

        public Role build() {
            if (name == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Role: поле name не может быть null"
                );
            }
            return new Role(this);
        }
    }
}
