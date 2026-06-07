package service;

import java.util.ArrayList;
import java.util.UUID;

import model.TrackerItem;

/*
 * tracks tracker items; offers getter methods 
 */
public abstract class TrackerService<T extends TrackerItem> {
    protected ArrayList<T> itemList = new ArrayList<>();

    /*
     * track a new tracker item
     * @param item to be tracked; not null
     * @return TrackerServiceResult
     */
    protected TrackerServiceResult addItem(T item) {
        if (item == null) {
        	throw new IllegalArgumentException("Item can't be null!");
        }
        
        if (itemList.contains(item)) {
        	return TrackerServiceResult.ALREADY_IN_LIST;
        }
    	itemList.add(item);
    	return TrackerServiceResult.SUCCESS;
    }

    /*
     * untrack an item by id
     * @param id of item; not null
     * @return TrackerServiceResult
     */
    protected TrackerServiceResult deleteItemById(UUID id) {
    	if (id == null) {
    		throw new IllegalArgumentException("Id can't be null!");
    	}
    	 if (itemList.removeIf(item -> item.getId().equals(id))) {
    		 return TrackerServiceResult.SUCCESS;
    	 }
    	
        return TrackerServiceResult.NOT_IN_LIST;
    }

    /*
     * find a tracked item
     * @param id of item; not null
     * @return item
     */
    protected T findItemById(UUID id) {
    	if (id == null) {
    		throw new IllegalArgumentException("Id can't be null!");
    	}
    	
        for (T item : itemList) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /*
     * return a list of all tracked items
     * @return ArrayList of items
     */
    protected ArrayList<T> getAllItems() {
        return new ArrayList<>(itemList);
    }
}
