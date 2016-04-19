
package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class NEWSFeedModel {

    private String title;
    private ArrayList<NEWSModel> rows = new ArrayList<NEWSModel>();
    public NEWSFeedModel(){
    	
    }
    public NEWSFeedModel(String title,ArrayList<NEWSModel> rows){
    	this.title = title;
    	this.rows = rows;
    }
    
    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The rows
     */
    public ArrayList<NEWSModel> getRows() {
        return rows;
    }

    /**
     * 
     * @param rows
     *     The rows
     */
    public void setRows(ArrayList<NEWSModel> rows) {
        this.rows = rows;
    }

}
