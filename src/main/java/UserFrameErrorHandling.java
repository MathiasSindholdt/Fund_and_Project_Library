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

    public static JPanel displayAmountFromError(){
        JOptionPane.showMessageDialog(null, "Fra beløbet skal være et tal");
        return new JPanel();
    }

    public static JPanel displayAmountToError(){
        JOptionPane.showMessageDialog(null, "Til beløbet skal være et tal");
        return new JPanel();
    }

    public static JPanel displayWebsiteError(){
        JOptionPane.showMessageDialog(null, "Websitet skal være i formatet www.example.com");
        return new JPanel();
    }

    public static JPanel displayAllTextFieldError(){
        JOptionPane.showMessageDialog(null, "Alle felter skal være udfyldt");
        return new JPanel();
    }

    public static JPanel displayPhoneError(){
        JOptionPane.showMessageDialog(null, "Ugyldigt telefonnummer");
        return new JPanel();
    }

    public static JPanel displayEmailError(){
        JOptionPane.showMessageDialog(null, "Ugyldig email, email skal være i formatet example@example.com");
        return new JPanel();
    }

    public static JPanel displayTimeError(){
        JOptionPane.showMessageDialog(null, "Ugyldig tid, tid skal være i formatet hh:mm, og tiden skal være mellem 00:00 og 23:59");
        return new JPanel();
    }
    //Error Handling for fields that take 2x dates as input
    public static JPanel displayDateSpinnerError(){
        JOptionPane.showMessageDialog(null, "Fra datoen skal være før til datoen");
        return new JPanel();
    }
    
    public static JPanel displayIdeaError(Boolean isInvalidLenght){
        if(isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ideen skal være mellem 1 og 2000 tegn");
        } else if(!isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ugyldige tegn i ideen");
        }
        return new JPanel();
    }

    public static JPanel displayNameError(Boolean isInvalidLenght){
        if(!isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Navnet skal være mellem 1 og 200 tegn");
        } else if(isInvalidLenght){
            JOptionPane.showMessageDialog(null, "Ugyldige tegn i navnet");
        }
        return new JPanel();
    }
}