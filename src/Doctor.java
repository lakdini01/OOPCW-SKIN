import java.time.LocalDate;
import java.util.List;

public class Doctor extends Person{
    private String medicalLicence;
    private String[] specialisation = new String[]{"Cosmetic Dermatology","Medical Dermatology", "Paediatric Dermatology" };

    public Doctor(String medicalLicence, String[] specialisation, String name, String surname, LocalDate dateOfBirth, String mobile) {
        super(name, surname, dateOfBirth, mobile);
        this.medicalLicence = medicalLicence;
        this.specialisation = specialisation;
    }

    public Doctor(){}

    public String getMedicalLicence() {
        return medicalLicence;
    }

    public void setMedicalLicence(String medicalLicence) {
        this.medicalLicence = medicalLicence;
    }

    public String[] getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String[] specialisation) {
        this.specialisation = specialisation;
    }
}
