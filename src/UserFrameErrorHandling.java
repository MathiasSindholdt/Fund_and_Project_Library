import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.*;

public class UserFrameErrorHandling{
    public static JPanel displayTitleError(){
        JOptionPane.showMessageDialog(null, "Titlen skal være udfyldt");
        return new JPanel();
    }
    public static JPanel displayPurposeError(){
        JOptionPane.showMessageDialog(null, "Formålet skal være udfyldt");
        return new JPanel();
    }
    public static JPanel displayDescriptionError(){
        JOptionPane.showMessageDialog(null, "Beskrivelsen skal være udfyldt");
        return new JPanel();
    }
    public static JPanel displayOwnerError(){
        JOptionPane.showMessageDialog(null, "Ejer skal være udfyldt");
        return new JPanel();
    }
    public static JPanel displayTargetAudienceError(){
        JOptionPane.showMessageDialog(null, "Målgruppen skal være udfyldt");
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
    public static JPanel displayActivityError(){
        JOptionPane.showMessageDialog(null, "Aktiviteterne skal være udfyldt");
        return new JPanel();
    }
    public static JPanel displayTagError(){
        JOptionPane.showMessageDialog(null, "Katagorien eksisterer allerede");
        return new JPanel();
    }
}