package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class Directory {
    private String name;
    private Directory parent;
    private Map<String, Directory> children;

    public Directory(String name) {
        this.name = name;
        this.children = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public Map<String, Directory> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Directory> children) {
        this.children = children;
    }
}
