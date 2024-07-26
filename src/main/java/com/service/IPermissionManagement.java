package com.service;

import com.model.datatype.Person;
import com.model.Permission;

import java.util.List;
import java.util.Map;

public interface IPermissionManagement {

    void addPermission(Person p, Permission ps);

    void removePermission(Person p, Permission ps);

    Map<Person, List<Permission>> getMap();
}
