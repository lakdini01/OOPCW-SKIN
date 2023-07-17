import java.util.Scanner;

public interface SkinConsultationManager {
    void addDocInfo(Scanner input);
    void deleteDocInfo(Scanner input);
    void printDocInfo();
    void saveDocInfo();
    void loadDocInfo();
    void openGUI();
    void savePatientInfo();
    void loadPatientInfo();
    void saveConsultationInfo();
    void loadConsultationInfo();
}
