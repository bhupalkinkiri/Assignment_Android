
package com.example.model;

public class NEWSModel {

    private String title;
    private String description;
    private String imageHref;

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
    public Object getImageURLRef() {
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
    	return title+" , "+description+" , " +imageHref;
    }

}
