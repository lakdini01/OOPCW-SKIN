import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public static ArrayList<Doctor> docInfo = new ArrayList<>();
    public static ArrayList<Patient> patientInfo = new ArrayList<>();
    public static ArrayList<Consultation> consultationInfo = new ArrayList<>();

    Doctor doc = new Doctor();
    Person person = new Person();
    public void addDocInfo(Scanner input) { //Doctors get added to the system

        String newName;
        String newSurname;
        LocalDate newDateOfBirth = null;
        String newMobile;

        if (docInfo.size() <= 9) {
            while (true) {
                System.out.println("Enter your name: "); // adding the doctor's first name
                newName = input.next();
                newName = newName.trim();
                if (!newName.matches("[a-z A-Z]*")) { //checking whether the input has only letters and spaces
                    System.out.println("Invalid name! Please try again.");
                } else {
                    person.setName(newName.toUpperCase());
                    break;
                }
            }

            while (true) {
                System.out.println("Enter your surname: "); // adding the doctor's second name
                newSurname = input.next();
                newSurname = newSurname.trim();
                if (!newSurname.matches("[a-z A-Z]*")) {
                    System.out.println("Invalid Surname! Please try again.");
                } else {
                    person.setSurname(newSurname.toUpperCase());
                    break;
                }
            }

            try {
                String dob;
                System.out.println("Enter your date of birth (Please enter as yyyy-mm-dd): "); // adding the doctor's date of birth
                dob = input.next();
                while (!dob.matches("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$")) { //checking whether it's an actual date
                    System.out.println("Invalid date of birth! Please try again.");
                    System.out.println("Enter your date of birth (Please enter as yyyy-mm-dd): ");
                    dob = input.next();
                }
                person.setDateOfBirth(LocalDate.parse(dob));
                newDateOfBirth = person.getDateOfBirth();

            } catch (DateTimeParseException e) {
                System.out.println("Please enter the date of birth as described");
            }


            System.out.println("Enter your mobile number: "); //adding the doctor's mobile number
            newMobile = input.next();
            while (newMobile.length() != 10) { //checking whether the mobile number only has 10 digits
                System.out.println("Invalid mobile number! Please try again.");
                System.out.println("Enter your mobile number: ");
                newMobile = input.next();
            }

            person.setMobile(newMobile);
            newMobile = person.getMobile();

            String medNum;
            System.out.println("Enter your medical license number: "); //adding the medical license number
            medNum = input.next();
            while (!medNum.matches("^[0-9]{3}$")){ //the medical license number should only have 3 digits
                System.out.println("Invalid license number! Please try again.");
                System.out.println("Enter your medical license number: ");
                medNum = input.next();
            }
            doc.setMedicalLicence(medNum);
            String newMedicalLicence = doc.getMedicalLicence();


            System.out.println("Enter your specialization(Cosmetic Dermatology/Medical Dermatology/Paediatric Dermatology): "); //adding the specialization of the doctor
            input.nextLine();
            doc.setSpecialisation(new String[]{input.nextLine()});
            String[] newSpecialization = doc.getSpecialisation();
            //String[] specs = new String[]{"Cosmetic Dermatology","Medical Dermatology", "Paediatric Dermatology"}

            Doctor doctor = new Doctor(newMedicalLicence, newSpecialization, newName, newSurname, newDateOfBirth, newMobile);

            docInfo.add(doctor);
        } else {
            System.out.println("Only 10 doctors can be added.");
        }


    }

    public void deleteDocInfo(Scanner input) { //deleting a doctor and his/her information

        System.out.println("Enter your medical licence number: ");
        String medicalLicence = input.next();

        Doctor doctor = new Doctor();
        boolean isAvailable = false;
        for (Doctor doc : docInfo) {
            if (doc.getMedicalLicence().equals(medicalLicence)) {
                doctor = doc;
                isAvailable = true;
                break;
            }
        }
        if (isAvailable) {
            docInfo.remove(doctor);
            System.out.println("Doctor is successfully removed!");
        }
        else {
            System.out.println("No one was removed");
        }
    }

    public void printDocInfo() { //this method prints all information that was put into the system
        if (docInfo.isEmpty()) {
            System.out.println("There are no doctors available");
        } else {
            docInfo.sort((doc1, doc2) ->
            {
                String x = doc1.getSurname();
                String y = doc2.getSurname();
                return x.compareToIgnoreCase(y);
            });
            for (Doctor d1 : docInfo) {
                System.out.println("Name: " + d1.getName() + ", Surname: " + d1.getSurname() + ", Date of Birth: " + d1.getDateOfBirth() + ", Mobile No: " + d1.getMobile() + ", Medical Licence: " + d1.getMedicalLicence() + ", specialization: " + Arrays.toString(d1.getSpecialisation()));
            }

        }

    }

    public void saveDocInfo() { //this method saves the doctor's information to a text file
        try {
            FileOutputStream fileOut = new FileOutputStream("Doctor Details.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(docInfo);
            out.flush();
            out.close();
            System.out.println("Doctor's information saved successfully");
        } catch (IOException i) {
            System.out.println("Sorry,Error occurred.");
            i.printStackTrace();
        }
    }

    public void loadDocInfo() { // from this method loads the data that was saved in a text file in the previous method
        try {
            FileInputStream input = new FileInputStream("Doctor Details.txt");
            ObjectInputStream load = new ObjectInputStream(input);
            docInfo = (ArrayList<Doctor>) load.readObject();
            System.out.println("Data loaded successfully");
            load.close();
            input.close();
        } catch (IOException notFoundException) {
            System.out.println("There's no such file!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void openGUI() { //this opens the GUI
        System.out.println("The GUI will appear");
        GUI gui = new GUI();
    }

    public void savePatientInfo() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Patient Details.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(patientInfo);
            out.flush();
            out.close();
            System.out.println("Patient's information saved successfully");
        } catch (IOException i) {
            System.out.println("Sorry,Error occurred.");
            i.printStackTrace();
        }
    }

    public void loadPatientInfo() {
        try {
            FileInputStream input = new FileInputStream("Patient Details.txt");
            ObjectInputStream load = new ObjectInputStream(input);
            patientInfo = (ArrayList<Patient>) load.readObject();
            System.out.println("Data loaded successfully");
            load.close();
            input.close();
        } catch (IOException notFoundException) {
            System.out.println("There's no such file!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveConsultationInfo() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Consultation Details.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(consultationInfo);
            out.flush();
            out.close();
            System.out.println("Consultation information saved successfully");
        } catch (IOException i) {
            System.out.println("Sorry,Error occurred.");
            i.printStackTrace();
        }
    }

    public void loadConsultationInfo() {
        try {
            FileInputStream input = new FileInputStream("Consultation Details.txt");
            ObjectInputStream load = new ObjectInputStream(input);
            consultationInfo = (ArrayList<Consultation>) load.readObject();
            System.out.println("Data loaded successfully");
            load.close();
            input.close();
        } catch (IOException notFoundException) {
            System.out.println("There's no such file!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
