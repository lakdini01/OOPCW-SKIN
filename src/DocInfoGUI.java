import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DocInfoGUI extends JFrame {
    ArrayList<Doctor> d1 = WestminsterSkinConsultationManager.docInfo;

    public DocInfoGUI(){ //Creating a table to show the doctor's information
        setVisible(true);
        setSize(1200, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] tableColumns = {"Name", "Sur Name", "DOB", "Mobile No", "M.L. No", "Specialisation"};
        Object[][] data = new Object[d1.size()][6];
        int index = 0;
        for (Doctor doctor : d1) {
            data[index][0] = doctor.getName();
            data[index][1] = doctor.getSurname();
            data[index][2] = doctor.getDateOfBirth();
            data[index][3] = doctor.getMobile();
            data[index][4] = doctor.getMedicalLicence();
            data[index][5] = doctor.getSpecialisation()[0];
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
