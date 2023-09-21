package ru.job4j.generics;

import java.util.List;

public class Role extends Base {

    private final String name;
    private List<String> permissions;

    public Role(String id, String name, List<String> permissions) {
        super(id);
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
