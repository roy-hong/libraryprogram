package LibraryInterface;

import java.io.Serializable;

public class Book implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String Title;
    private String ListofAuthors; 
    private int CatalogNumber;
    private String SubjectHeadings; 
    private String Publisher;
    private int PublicationYear;
    private Boolean circulating;
    
    
    public Book(String title) {
        Title = title;
    }
    
    public void setTitle(String inputTitle) {
    	Title = inputTitle;
    }
    
    public String getTitle() {
        return Title;
    }
    
    public void setListofAuthors(String lists) {
        ListofAuthors = lists;
    }
    
    public String getListofAuthors() {
        return ListofAuthors;
    }
    
    public void setCatalogNumber(int setnumber) {
        CatalogNumber = setnumber;
    }
    
    public int getCatalogNumber() {
        return CatalogNumber;
    }
    
    public void setSubjectHeadings(String headings) {
        SubjectHeadings = headings;
    }
    
    public String getSubjectHeadings() {
        return SubjectHeadings;
    }
    
    public void setPublisher(String name) {
        Publisher = name;
    }
    
    public String getPublisher() {
        return Publisher;
    }
    
    public void setPublicationYear(int theYear) {
        PublicationYear = theYear;
    }
    
    public int getPublicationYear() {
        return PublicationYear;
    }
    
    public void setcirculating(boolean cirinput) {
        circulating = cirinput;
    }
    
    public boolean getCirculating() {
        return circulating;
    }

    public String toString() {
    	return ( "Title : " + this.getTitle() +"    Authors : "+ this.getListofAuthors()+"    Catalog No. : " + this.getCatalogNumber() +
    			"	    Subject :  "+this.getSubjectHeadings()+"    Publisher : " + this.getPublisher()+
    			"    Pub' Year : "+this.getPublicationYear() +"	    Cir : "+this.getCirculating());
    }// All the Information that the Book has
}

