package com;

import com.column.datatype.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PermissionManagement implements IPermissionManagement {
    private Map<Person, List<Permission>> map;

    public PermissionManagement() {
        map = new HashMap<>();
    }

    public void addPermission(Person p, Permission ps) {
        map.computeIfAbsent(p, k -> new ArrayList<>()).add(ps);
    }

    public void removePermission(Person p, Permission ps) {
        Optional.ofNullable(map.get(p))
                .ifPresent(permits -> permits.remove(ps));
    }
}
