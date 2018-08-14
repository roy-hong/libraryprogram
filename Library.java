package LibraryInterface;

public class Library { 
	/*
	The Search Method is deleted at this time 
	since it is going to be implemented in Swing.
	*/
    public static int INITSIZE = 100;
    public static int count = 0;
    Book[] list = new Book[INITSIZE];
    
    public void add(Book addedbook) {
        
        if(count == list.length) { //check if exists
            Book[] temp = new Book[2*list.length];
            System.arraycopy(list, 0, temp, 0, count);
            list = temp;
        }
        
        list[count] = addedbook;
        count++;
    }
    
    
}
