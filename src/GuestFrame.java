import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GuestFrame implements ActionListener {

    // GUI komponenter
    private JFrame frame;
    private JButton createProbButton;
    private JButton changeProbButton; 
    private JButton backButton;
    private JButton projectPropButton;
    private JButton projectButton;
    private JButton fundsButton;

    // Constructor til at opsætte GUI
    public GuestFrame() {
        initializeFrame();  // Initialiserer JFrame
        JPanel panel1 = createTopPanel();  // Opretter top-panel
        JPanel panel2 = createSidePanel();  // Opretter side-panel
        JPanel panel5 = createCenterPanel();  // Opretter center-panel

        // Tilføj paneler til frame
        frame.add(panel1, BorderLayout.NORTH);  
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel5, BorderLayout.CENTER);  
    }

    // Initialiserer JFrame
    private void initializeFrame() {
        frame = new JFrame("Gæst");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout(10, 10));
    }

    // Opretter top-panel med tilbageknap, label, og projekt knap
    private JPanel createTopPanel() {
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(213, 213, 213, 255)); 
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Elementer justeres til venstre med lidt afstand

        // Tilføj tilbageknap
        backButton = createBackButton();
        panel1.add(backButton);

        // Tilføj label
        JLabel label = new JLabel("Gæst", SwingConstants.LEFT);
        panel1.add(label);

        // Kald den korrekt oprettede knap
        projectPropButton = createProjectPropButton("Projekt forslag");
        panel1.add(projectPropButton);

        // Kald den korrekt oprettede knap
        projectButton = createProjectButton("Projekter");
        panel1.add(projectButton);

        fundsButton = createFundsButton("Fonde");
        panel1.add(fundsButton);

        return panel1;
    }

    // Opretter side-panel (kan fyldes med indhold senere)
    private JPanel createSidePanel() {
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(213, 213, 213, 255));
        panel2.setPreferredSize(new Dimension(100, 100));
        return panel2;
    }

    // Opretter center-panel med knapper
    private JPanel createCenterPanel() {
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel5.setBackground(new Color(213, 213, 213, 255));

        // Tilføj knapper til panel5
        createProbButton = createButton("Lav projekt forslag");
        changeProbButton = createButton("Ændre projekt forslag");

        panel5.add(createProbButton);
        panel5.add(changeProbButton);

        return panel5;
    }

    // Opretter en knap med action listener
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.addActionListener(this);
        return button;
    }

    // Opretter projekt forslag knap
    private JButton createProjectPropButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));  // Du kan justere størrelsen på knappen her
        button.addActionListener(this);
        return button;
    }
    private JButton createProjectButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));  // Du kan justere størrelsen på knappen her
        button.addActionListener(this);
        return button;
    }

    private JButton createFundsButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));  // Du kan justere størrelsen på knappen her
        button.addActionListener(this);
        return button;
    }

    // Opretter tilbageknappen
    private JButton createBackButton() {
        // Load and resize the icon image for the back button
        ImageIcon originalIcon = new ImageIcon("backArrow.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        button.setIcon(resizedIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
        return button;
    }

    // Metode til at vise vinduet
    public void show() {
        frame.setVisible(true);
    }

    // Håndterer knap klik begivenhed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createProbButton) {
            JOptionPane.showMessageDialog(frame, "Projekt forslag knap klikket!");
        } else if (e.getSource() == changeProbButton) {
            JOptionPane.showMessageDialog(frame, "Ændre projekt forslag knap klikket!");
        } else if (e.getSource() == backButton) {
            JOptionPane.showMessageDialog(frame, "Tilbage knap klikket!");
        } else if (e.getSource() == projectPropButton) {
            JOptionPane.showMessageDialog(frame, "Projekt forslag knap klikket!");
        }
    }

    // SKAL FJERNES, Main metode for at køre applikationen 
    public static void main(String[] args) {
        GuestFrame guestFrame = new GuestFrame();
        guestFrame.show();
    }
}
