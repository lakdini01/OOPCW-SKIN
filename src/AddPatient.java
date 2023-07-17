import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class AddPatient extends JFrame {

    private JLabel name;
    private JTextField nameT;
    private JLabel surName;
    private JTextField surNameT;
    private JLabel dob;
    private JTextField dobT;
    private JLabel mobNum;
    private JTextField mobNumT;
    private JLabel id;
    private JTextField idT;
    private JButton close;
    private JButton addPatient;
    private JLabel sent;
    private JLabel dobL;
    private JLabel notes;
    private JTextField notesT;

    static LocalDate consultationDate;
    static LocalTime startTime;
    static LocalTime endTime;
    static Doctor doctor;

    public AddPatient() {

        setTitle("Add Patient");
        setSize(404, 500);
        setLayout (null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        //construct components

        name = new JLabel ("Name: ");
        nameT = new JTextField (5);
        surName = new JLabel ("Sur Name: ");
        surNameT = new JTextField (5);
        dob = new JLabel ("Date of Birth: ");
        dobT = new JTextField (5);
        mobNum = new JLabel ("Mobile Number: ");
        mobNumT = new JTextField (5);
        id = new JLabel ("ID: ");
        idT = new JTextField (5);
        close = new JButton ("Close");
        addPatient = new JButton ("Add Patient");
        sent = new JLabel ("Please give in the following information to get a consultation");
        dobL = new JLabel ("yyyy-mm-dd");
        notes = new JLabel("notes: ");
        notesT = new JTextField(5);


        //add components
        add (name);
        add (nameT);
        add (surName);
        add (surNameT);
        add (dob);
        add (dobT);
        add (mobNum);
        add (mobNumT);
        add (id);
        add (idT);
        add (close);
        add (addPatient);
        add (sent);
        add (dobL);
        add (notes);
        add(notesT);

        //setting component bounds
        name.setBounds (50, 75, 100, 25);
        nameT.setBounds (155, 75, 175, 30);
        surName.setBounds (50, 130, 100, 25);
        surNameT.setBounds (155, 130, 175, 30);
        dob.setBounds (50, 185, 100, 25);
        dobT.setBounds (155, 185, 150, 30);
        mobNum.setBounds (50, 240, 100, 25);
        mobNumT.setBounds (155, 240, 135, 30);
        id.setBounds (50, 295, 100, 25);
        idT.setBounds (155, 295, 185, 30);
        close.setBounds (260, 415, 100, 25);
        addPatient.setBounds (50, 415, 100, 25);
        sent.setBounds (20, 5, 370, 60);
        dobL.setBounds (315, 190, 100, 25);
        notes.setBounds(50,350, 100,25);
        notesT.setBounds(155, 350, 185,30);

        ActionHandler actHand = new ActionHandler();
        close.addActionListener(actHand);
        addPatient.addActionListener(actHand);
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == close) {
                AddConsultation gui = new AddConsultation();
                dispose();
            }

            if(e.getSource() == addPatient){

                if (nameT.getText().equals("") || surName.getText().equals("") || mobNum.getText().equals("") || idT.getText().equals("") || notesT.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields!", "Fill the blanks", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    ArrayList<Patient> patients = WestminsterSkinConsultationManager.patientInfo;
                    String name = nameT.getText();
                    String surName = surNameT.getText();
                    LocalDate dob = LocalDate.parse(dobT.getText());
                    String mobNum = mobNumT.getText();
                    String id = idT.getText();
                    String notes = notesT.getText();
                    Patient patient = new Patient();
                    patient.setName(name);
                    patient.setSurname(surName);
                    patient.setDateOfBirth(dob);
                    patient.setMobile(mobNum);
                    patient.setId(id);

                    int cost = costCalculator(endTime.getHour() - startTime.getHour(), id);
                    JOptionPane.showMessageDialog(null, "Consultation Cost is : " + cost + ".00", "Consultation Cost", JOptionPane.INFORMATION_MESSAGE);

                    patients.add(patient);

                    Consultation consultation = new Consultation(consultationDate, startTime, endTime, cost, notes, doctor, patient);
                    WestminsterSkinConsultationManager.consultationInfo.add(consultation);
                    JOptionPane.showMessageDialog(null, "Successfully added the consultation!", "Consultation is added", JOptionPane.INFORMATION_MESSAGE);
                    GUI gui = new GUI();
                    dispose();
                }
            }
        }
    }
    public int costCalculator(int timePeriod, String patientID) {
        int cost = 0;
        for (Patient patient : WestminsterSkinConsultationManager.patientInfo) {
            if (patient.getId().equalsIgnoreCase(patientID)) {
                cost = timePeriod * 25;
                return cost;
            }
        }
        cost = timePeriod * 15;
        return cost;
    }

}

