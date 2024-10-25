import java.time.LocalDateTime;
public class fundConstructor extends fundClass {
    public fundConstructor(
        String fundName,
        String fundDescription, 
        long fundAmount,
        LocalDateTime[] fundDeadline,
        String[] fundCategory,
        String[] fundCollaborationHistory,
        String[] fundContacts,
        boolean Collaborated){
        this.setTitle(fundName);
        this.setDescription(fundDescription);
        this.setCommonBudget(fundAmount);
        for(int i = 0; i < fundDeadline.length ; i++){
            this.setDeadlines(fundDeadline[i]);
        }
        for(int i = 0; i < fundCategory.length ; i++){
            this.setCategories(fundCategory[i]);
        }
        for(int i = 0; i < fundContacts.length ; i++){
            this.setContacts(fundContacts[i]);
        }
        if(Collaborated){
            for(int i = 0; i < fundCollaborationHistory.length ; i++){
                this.setCollaborationHistory(fundCollaborationHistory[i]);
            }
        }
    }
}
