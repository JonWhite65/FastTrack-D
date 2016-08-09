package com.cooksys.collections;

public class Manager implements Employee {

    private String name;
    private String title;
    private Manager manager;

    public Manager(String name, String title, Manager manager) {
        this.name = name;
        this.title = title;
        this.manager = manager;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", manager=" + manager +
                '}';
    }
}
