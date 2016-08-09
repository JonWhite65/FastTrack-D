package com.cooksys.collections;

public interface Employee {
    void setName(String name);

    String getName();

    void setTitle(String title);

    String getTitle();

    void setManager(Manager manager);

    Manager getManager();
}
