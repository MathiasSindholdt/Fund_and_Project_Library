import java.util.ArrayList;
import java.time.LocalDateTime;

public class removedPassedDeadline {
    public ArrayList<fundClass> removePassedDeadlines(ArrayList<fundClass> listOfFunds) {
        ArrayList<fundClass> withinTimespan = new ArrayList<>();
        LocalDateTime dateChecked = LocalDateTime.now();
        
        for (fundClass fund : listOfFunds) {
            boolean added = false;  // Flag to ensure fund is only added once
            for (LocalDateTime deadline : fund.getDeadlines()) {
                if (deadline.isAfter(dateChecked) && !added) {
                    System.out.println("Found a deadline further out: " + deadline + " for fund: " + fund.getTitle());
                    withinTimespan.add(fund);
                    added = true;  // Ensure the fund is added only once
                }
            }
        }
        return withinTimespan;
    } 
}
