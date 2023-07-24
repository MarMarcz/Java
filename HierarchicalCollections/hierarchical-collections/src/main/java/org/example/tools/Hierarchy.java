package org.example.tools;

import org.example.model.abstraction.IHaveHierarchicalStructure;


public class Hierarchy<TItem extends IHaveHierarchicalStructure<TItem>>{
    private TItem rootElement;
    private int index;


    public void setRootElement(TItem tItem) {
        this.rootElement = tItem;
    }



    public TItem findElementById(int id){
        this.index=id;
        if(rootElement.getId()==id){
            return rootElement;
        }
        return searchChildren(rootElement);
    }

    private TItem searchChildren(TItem item){
        for (TItem element:item.getChildren()) {
            if(element.getId()==index){
                return element;
            }
            else if(!item.getChildren().isEmpty()){
                TItem result = searchChildren(element);
                if(result != null)
                    return result;
            }
        }
        return null;
    }
}
