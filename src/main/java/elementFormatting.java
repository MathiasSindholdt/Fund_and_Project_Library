import java.time.LocalDateTime;
import java.util.ArrayList;

public class elementFormatting {
    private String title;
    private ArrayList<String> categories = new ArrayList<String>();
    private String description;
    private LocalDateTime dateCreated = LocalDateTime.now();


    public String getTitle(){
        return title;
    }

    public ArrayList<String> getCategories(){
        return categories;
    }

    public String getDescription(){
        return description;
    }


    public LocalDateTime getDateCreated(){
        return dateCreated;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setCategories(String newCategory){
        this.categories.add(newCategory);
    }


    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public void setDateCreated(LocalDateTime newDateCreated){
        this.dateCreated = newDateCreated;
    }

    public static void main(String[] args) {
        String[] testCatories = {"cat1","cat2","cat3"};
        elementFormatting abstractObject = new elementFormatting();
        abstractObject.setTitle("testTitle");
        abstractObject.setDescription("Lorem Ipsum");
        for (int i = 0; i < testCatories.length ; i++){
            abstractObject.setCategories(testCatories[i]);
        }
        System.out.println("Here is the abstractElement: " + abstractObject.getTitle());
        System.out.println("It has the following description: " + abstractObject.getDescription());
        System.out.println("It was created at: " + abstractObject.getDateCreated());
        System.out.println("It has the following categoreis: " + abstractObject.getCategories());
    }
}
