package com.cooksys.collections;

public class Grunt implements Employee {

    private String name;
    private String title;
    private Manager manager;

    public Grunt(String name, String title, Manager manager) {
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
        return "Grunt{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", manager=" + manager +
                '}';
    }
}
