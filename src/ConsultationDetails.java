import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ConsultationDetails extends JFrame {
    ArrayList<Consultation> c = WestminsterSkinConsultationManager.consultationInfo;

    public ConsultationDetails(){
        setVisible(true);
        setSize(1400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] tableColumns ={"Patient Name", "Patient ID", "Doctor Name", "Doctor ID", "Doctor Specialization", "Consultation Date", "Consultation Start Time", "Consultation End Time", "Notes", "Cost"};
        Object[][] data = new Object[c.size()][10];
        int index = 0;
        for (Consultation consultation : c){
            data[index][0] = consultation.getPatient().getName();
            data[index][1] = consultation.getPatient().getId();
            data[index][2] = consultation.getDoctor().getName();
            data[index][3] = consultation.getDoctor().getMedicalLicence();
            data[index][4] = consultation.getDoctor().getSpecialisation()[0];
            data[index][5] = consultation.getDate();
            data[index][6] = consultation.getStartTime();
            data[index][7] = consultation.getEndTime();
            data[index][8] = consultation.getNotes();
            data[index][9] = consultation.getCost();
            index++;
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, tableColumns);
        JTable doctorTable = new JTable(tableModel);
        doctorTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        doctorTable.setAutoCreateRowSorter(true);

        add(scrollPane);
    }
}
