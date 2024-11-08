import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class getCurrentCatagories {
    public static void getAllCurrentCatagories(JPanel tagPanel){
        for(int i = 0; i < main.categories.size(); i++){
            JCheckBox tag = new JCheckBox(main.categories.get(i));
            tagPanel.add(tag);
        }
    }
}
