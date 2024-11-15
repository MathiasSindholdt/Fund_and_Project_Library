public class fundContactClass {

    private String contactName;
    private String contactEmail;
    private String contactPhoneNumber;

    public fundContactClass(String contactName, String contactPhoneNumber, String contactEmail) {
        this.contactName = "";
        this.contactEmail = "";
        this.contactPhoneNumber = "";

        this.setContactName(contactName);
        this.setContactEmail(contactEmail);
        this.setContactPhoneNumber(contactPhoneNumber);
    }
    //Setters

    public void setContactName(String newContactName){
        this.contactName = newContactName;
    }

    public void setContactEmail(String newContactEmail){
        this.contactEmail = newContactEmail;
    }

    public void setContactPhoneNumber(String newContactPhoneNumber){
        this.contactPhoneNumber = newContactPhoneNumber;
    }

    //Getters
    public String getContactName(){
        return contactName;
    }

    public String getContactEmail(){
        return contactEmail;
    }

    public String getContactPhoneNumber(){
        return contactPhoneNumber;
    }

}
