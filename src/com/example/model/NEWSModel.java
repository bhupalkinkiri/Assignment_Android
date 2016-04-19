
package com.example.model;

public class NEWSModel {

    private String title;
    private String description;
    private String imageHref;

    public NEWSModel(){}
    public NEWSModel(String title,String description,String imageHref){
    	this.title = title;
    	this.description = description;
    	this.imageHref = imageHref;
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
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The imageHref
     */
    public String getImageURLRef() {
        return imageHref;
    }

    /**
     * 
     * @param imageHref
     *     The imageHref
     */
    public void setImageURLRef(String imageHref) {
        this.imageHref = imageHref;
    }
    
    public String getString(){
    	return title+" , "+description+" , " +imageHref+"\n\n";
    }

}
