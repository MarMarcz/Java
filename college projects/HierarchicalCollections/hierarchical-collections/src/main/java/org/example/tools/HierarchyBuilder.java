package org.example.tools;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.List;

public class HierarchyBuilder<TItem extends IHaveHierarchicalStructure<TItem>>{
    private List<TItem> elements;


    public List<TItem> getElements() {
        return elements;
    }

    public void setElements(List<TItem> elements) {
        this.elements = elements;
    }

    public void buildHierarchy() {
        for (TItem tItem: elements) {
            if (tItem.getParentId() == null) {

            } else {
                int parentId = tItem.getParentId(); //znajdz parent id
                for (TItem item: elements) {
                    if (item.getId() == parentId) {
                        tItem.setParent(item); //ustawiasz rodzica
                        item.getChildren().add(tItem);
                    }
                }
            }
        }
    }

    public TItem getRootElement() {
        for (TItem tItem: elements) {
            if(tItem.getParentId() == null){
                return tItem;
            }
        }
        return null;
    }


}
