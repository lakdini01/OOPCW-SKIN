import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class AddConsultation extends JFrame {
    private JLabel addCons;
    private JLabel sent1;
    private JLabel sent2;
    private JLabel contDate;
    private JComboBox date;
    private JLabel ConsultTime;
    private JComboBox startTime;
    private JLabel to;
    private JComboBox endTime;
    private JLabel searchDoc;
    private JTextField docId;
    private JButton search;
    private JComboBox month;
    private JComboBox year;

    ArrayList<Doctor> doctors = WestminsterSkinConsultationManager.docInfo;
    String[] dateItems = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] monthItems = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] yearItems = {"2022","2023"};
    String[] startTimeItems = {"09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"};
    String[] endTimeItems = {"10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"};

    JComboBox<String> comboConsultDates = new JComboBox<>(dateItems);
    JComboBox<String> comboConsultMonths = new JComboBox<>(monthItems);
    JComboBox<String> comboConsultYears = new JComboBox<>(yearItems);
    JComboBox<String> comboStartTime = new JComboBox<>(startTimeItems);
    JComboBox<String> comboEndTime = new JComboBox<>(endTimeItems);

    public AddConsultation() {

        setTitle("Add Consultation");
        setSize(484, 557);
        setLayout (null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        //construct components
        addCons = new JLabel ("Add Consultation");
        sent1 = new JLabel ("Each consultation hour will cost 24/=");
        sent2 = new JLabel ("1ST CONSULTATION HOUR WILL ONLY COST 15/=");
        contDate = new JLabel ("Date of Consultation");
        date = new JComboBox (dateItems);
        month = new JComboBox (monthItems);
        year = new JComboBox (yearItems);
        ConsultTime = new JLabel ("Time of the consultation");
        startTime = new JComboBox (startTimeItems);
        to = new JLabel ("to");
        endTime = new JComboBox (endTimeItems);
        searchDoc = new JLabel ("Search Doc by ID");
        docId = new JTextField (5);


        search = new JButton ("Search");
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                String day = (String) comboConsultDates.getSelectedItem();
                String month = (String) comboConsultMonths.getSelectedItem();
                String year = (String) comboConsultYears.getSelectedItem();
                String date = day + "-" + month + "-" + year;

                String licenseNumber = docId.getText();
                boolean haveDoctor = false;
                for (Doctor doctor : doctors)
                {
                    if (doctor.getMedicalLicence().equals(licenseNumber))
                    {
                        haveDoctor = true;
                    }
                }

                if (haveDoctor)
                {
                    LocalDate consultDate = LocalDate.parse(date, formatter);
                    LocalTime startTime = LocalTime.parse((CharSequence) comboStartTime.getSelectedItem());
                    LocalTime endTime = LocalTime.parse((CharSequence) comboEndTime.getSelectedItem());


                    Doctor doctor = new Doctor();
                    boolean noDoctors = false;
                    List<Doctor> availableDoctorList = new ArrayList<>();
                    if (!isDocAvailable(licenseNumber, consultDate, startTime, endTime)) {
                        for (Doctor d : WestminsterSkinConsultationManager.docInfo) {
                            if ((d.getMedicalLicence().equalsIgnoreCase(licenseNumber))) {
                                doctor = d;
                            }
                        }
                    } else {

                        System.out.println(WestminsterSkinConsultationManager.docInfo.size());
                        for (Doctor value : WestminsterSkinConsultationManager.docInfo) {
                            if (isDocAvailable(licenseNumber, consultDate, startTime, endTime)) {
                                availableDoctorList.add(value);
                            }
                        }
                        if (availableDoctorList.size() == 0)
                        {
                            JOptionPane.showMessageDialog(null, "No doctors are available! Please select another time slot!", "Not Available", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }

                    if (!noDoctors)
                    {
                        int randomIndex = (int) (Math.random() * availableDoctorList.size());
                        if (availableDoctorList.size() == 0) {
                            String msg = "Doctor is Available";
                            JOptionPane.showMessageDialog(null, msg, "Available", JOptionPane.INFORMATION_MESSAGE);
                            docAvailability(msg, doctor, consultDate, startTime, endTime);


                        } else {
                            String msg = "Doctor selected randomly";
                            doctor = availableDoctorList.get(randomIndex);
                            JOptionPane.showMessageDialog(null, "Selected doctor is not available. Dr. " + doctor.getName() + " will be selected as the doctor.", "Not Available", JOptionPane.INFORMATION_MESSAGE);
                            docAvailability(msg, doctor,consultDate, startTime, endTime);
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "There is no doctor with this license number!", "No Doctor!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //add components
        add (addCons);
        add (sent1);
        add (sent2);
        add (contDate);
        add (date);
        add (month);
        add (year);
        add (ConsultTime);
        add (startTime);
        add (to);
        add (endTime);
        add (searchDoc);
        add (docId);
        add (search);

        //set component bounds (only needed by Absolute Positioning)
        addCons.setBounds (25, 25, 115, 30);
        sent1.setBounds (25, 65, 220, 25);
        sent2.setBounds (25, 105, 350, 20);
        contDate.setBounds (25, 150, 135, 25);
        date.setBounds (25, 185, 40, 20);
        month.setBounds (75, 185, 45, 20);
        year.setBounds (125, 185, 40, 20);
        ConsultTime.setBounds (225, 150, 150, 25);
        startTime.setBounds (185, 185, 100, 25);
        to.setBounds (300, 185, 25, 25);
        endTime.setBounds (325, 185, 100, 25);
        searchDoc.setBounds (25, 250, 100, 25);
        docId.setBounds (140, 250, 120, 25);
        search.setBounds (305, 250, 100, 25);
    }

    public void docAvailability(String msg, Doctor doctor, LocalDate date, LocalTime start, LocalTime end){
        JLabel docAvailable;
        JButton addPatient;

        //construct components
        docAvailable = new JLabel (msg, SwingConstants.CENTER);
        addPatient = new JButton ("Add Patient");

        addPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddPatient.consultationDate = date;
                AddPatient.startTime = start;
                AddPatient.endTime = end;
                AddPatient.doctor = doctor;
                AddPatient addPatient1 = new AddPatient();
                dispose();
            }
        });


        JPanel availabilityPanel = new JPanel();
        availabilityPanel.setLayout(null);
        availabilityPanel.setBounds(0,260, 484, 201);

        //add components
        availabilityPanel.add (docAvailable);
        availabilityPanel.add (addPatient);



        //set component bounds (only needed by Absolute Positioning)
        docAvailable.setBounds (0, 35, 484, 25);
        addPatient.setBounds (170, 90, 100, 25);

        add(availabilityPanel);

        this.revalidate();
        this.repaint();

    }
    public boolean isDocAvailable(String doctorLicenseNumber, LocalDate selectedDate, LocalTime selectedStartTime, LocalTime selectedEndTime) {
        if (!WestminsterSkinConsultationManager.consultationInfo.isEmpty())
        {
            for (Consultation consultation : WestminsterSkinConsultationManager.consultationInfo) {
                String licenseNumber = consultation.getDoctor().getMedicalLicence();

                if ((licenseNumber.equalsIgnoreCase(doctorLicenseNumber)) && (selectedDate.equals(consultation.getDate())) && (consultation.getEndTime().getHour() > selectedStartTime.getHour() || consultation.getStartTime().getHour() < selectedEndTime.getHour())) {
                    return true;
                }
            }
        }
        return false;
    }


}
