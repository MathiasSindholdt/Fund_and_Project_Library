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
        if (newTitle.length() > 200){
            throw new validationUtils.WrongDataInputException("Title length exceeds 200 characters.");
        }
        validationUtils.validateInput(newTitle, "Title");
        this.title = newTitle;
    }

    public void setCategories(String newCategory){

        if (newCategory.length() > 200){
            throw new validationUtils.WrongDataInputException("Category length exceeds 200 characters.");
        }

        validationUtils.validateInput(newCategory, "Category");
        this.categories.add(newCategory);
    }


    public void setDescription(String newDescription){
        if (newDescription.length() > 2000){
            throw new validationUtils.WrongDataInputException("Description length exceeds 2000 characters.");
        }
        validationUtils.validateInput(newDescription, "Description");
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
