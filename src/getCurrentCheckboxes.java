
import javax.swing.JCheckBox;
import javax.swing.JPanel;



public class getCurrentCheckboxes {
    public static void getAllCurrentProjects(JPanel projectPanel){
        for(int i = 0; i < main.projectList.size(); i++){
            JCheckBox projectCheckBox = new JCheckBox(main.projectList.get(i).getTitle());
            projectPanel.add(projectCheckBox);
        }
    }

    public static void getAllCurrentCatagories(JPanel tagPanel){
        for(int i = 0; i < main.categories.size(); i++){
            JCheckBox tag = new JCheckBox(main.categories.get(i));
            tagPanel.add(tag);
        }
    }

    public static void getAllProjects(JPanel projectPanel){
        //achived projects
        //current projects
        //userProjects
        for(int i = 0; i < main.projectList.size(); i++){
            JCheckBox projectCheckBox = new JCheckBox(main.projectList.get(i).getTitle());
            projectPanel.add(projectCheckBox);
        }
        for(int i = 0; i < main.userProjectList.size(); i++){
            JCheckBox projectCheckBox = new JCheckBox(main.userProjectList.get(i));
            projectPanel.add(projectCheckBox);
        }
        /*
         * for(int i = 0; i < main.acrhivedProject.size(); i++){
         * JCheckBox projectCheckBox = new JCheckBox(main.acrhivedProject.get(i).getTitle());
         * projectPanel.add(projectCheckBox);
         * }
         */
    }
}

