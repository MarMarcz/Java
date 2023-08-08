package org.example.model;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.ArrayList;
import java.util.List;

public class Geography implements IHaveHierarchicalStructure<Geography> {
    List<Geography> children = new ArrayList<>();
    Geography parent;
    private int id;
    private String name,type,code;
    private Integer parentId;

    public Geography() {
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setChildren(List<Geography> children) {
        this.children = children;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    @Override
    public void setParent(Geography geography) {
        this.parent = geography;
    }

    @Override
    public List<Geography> getChildren() {
        return children;
    }

    @Override
    public Geography getParent() {
        return parent;
    }
}
