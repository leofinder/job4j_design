package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        store.add(new Role("1", "User", List.of("create", "read", "update")));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceThenRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        store.replace("1", new Role("1", "User", List.of("create", "read", "update")));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("User");
    }

    @Test
    void whenNoReplaceAdminThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        store.replace("10", new Role("1", "User", List.of("create", "read", "update")));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        boolean result = store.replace("1", new Role("1", "User", List.of("create", "read", "update")));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin", List.of("create", "read", "update", "delete")));
        boolean result = store.replace("10", new Role("1", "User", List.of("create", "read", "update")));
        assertThat(result).isFalse();
    }
}