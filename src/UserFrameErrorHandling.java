import javax.swing.*;

public class UserFrameErrorHandling{
    public static JPanel displayTitleError(boolean isInvalidLenght){
        if (isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Titlen skal være mellem 1 og 200 tegn");
        } else if(!isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ugyldige tegn i titlen");
        }
        return new JPanel();
    }
    public static JPanel displayPurposeError(boolean isInvalidLenght){
        if(isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Formålet skal være mellem 1 og 2000 tegn");
        } else if(!isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ugyldige tegn i formålet");
        }
        return new JPanel();
    }
    public static JPanel displayDescriptionError(boolean isInvalidLenght){
        if(isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Beskrivelsen skal være mellem 1 og 2000 tegn");
        } else if(!isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ugyldige tegn i beskrivelsen");
        }
        return new JPanel();
    }
    public static JPanel displayOwnerError(boolean isInvalidLenght){
        if (isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ejer skal være mellem 1 og 200 tegn");
        } else if (!isInvalidLenght){ 
            JOptionPane.showMessageDialog(null, "Ugyldige tegn i ejer");
            
        }
        return new JPanel();
    }
    public static JPanel displayTargetAudienceError(boolean isInvalidLenght){
        if (isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Målgruppen skal være mellem 1 og 200 tegn");
        } else if (!isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ugyldige tegn i målgruppen");
        }
        return new JPanel();
    }
    public static JPanel displayBudgetError(){
        JOptionPane.showMessageDialog(null, "Budgettet skal være et tal");
        return new JPanel();
    }
    public static JPanel displayDateError(){
        JOptionPane.showMessageDialog(null, "Datoen skal være i formatet dd/MM/yyyy");
        return new JPanel();
    }
    public static JPanel displayActivityError(boolean isInvalidLenght){
        JOptionPane.showMessageDialog(null, "Aktiviteterne skal være udfyldt");
        return new JPanel();
    }
    public static JPanel displayTagError(){
        JOptionPane.showMessageDialog(null, "Katagorien eksisterer allerede");
        return new JPanel();
    }
}