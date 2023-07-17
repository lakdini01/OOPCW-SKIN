import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame {
    private JButton docInfo;
    private JButton AddConsult;
    private JButton ViewConsult;
    private JButton close;

    public GUI() { // this is the main GUI which leads you to the adding consultation, viewing doctor's information and viewing consultation details.

        setTitle("Skin Consultation");
        setSize(459, 420);
        setLayout (null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //construct components
        docInfo = new JButton ("Show doctor Information");
        AddConsult = new JButton ("Add Consultation");
        ViewConsult = new JButton ("View Consultation Details");
        close = new JButton ("Close");

        //adjust size and set layout
        setPreferredSize (new Dimension(459, 388));


        //add components
        add (docInfo);
        add (AddConsult);
        add (ViewConsult);
        add (close);

        //set component bounds (only needed by Absolute Positioning)
        docInfo.setBounds (125, 75, 205, 35);
        AddConsult.setBounds (125, 160, 205, 35);
        ViewConsult.setBounds (125, 240, 205, 35);
        close.setBounds (330, 335, 100, 25);

        ActionHandler actHand = new ActionHandler();
        docInfo.addActionListener(actHand);
        AddConsult.addActionListener(actHand);
        ViewConsult.addActionListener(actHand);
    }

    private class ActionHandler implements ActionListener{ //connecting docInfo GUI, addConsultation GUI and consultationDetails GUI

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == docInfo){
                DocInfoGUI docInfoGUI1 = new DocInfoGUI();
                dispose();
            }

            if(e.getSource() == AddConsult){
                AddConsultation addConsultation1 = new AddConsultation();
                dispose();
            }

            if (e.getSource() == ViewConsult){
                ConsultationDetails consultationDetails = new ConsultationDetails();
                dispose();
            }
        }
    }

}
