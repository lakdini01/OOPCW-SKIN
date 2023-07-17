import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NullPointerException {

        Scanner input = new Scanner(System.in);

        WestminsterSkinConsultationManager west = new WestminsterSkinConsultationManager();

        int num;

        try {
            System.out.println("""
                -------------Welcome to Westminster Skin Consultation!-------------
                                        
                If you want to add a doctor to the system, please enter                 => 1
                If you want to remove a doctor from the system, please enter            => 2
                If you want to see doctors' information, please enter                   => 3
                If you want to save the information to a file, please enter             => 4
                If you want to load data from the file, please enter                    => 5
                If you want to save patient information to a file, please enter         => 6
                If you want to load saved patient information, please enter             => 7
                If you want to save consultation information to a file, please enter    => 8
                If you want to load saved consultation information, please enter        => 9
                If you want to open the GUI, please enter                               => 10
                If you want to exit from the system, please enter                       => 11
                """);

            do {
                System.out.println("----------------------------------------------------------------------");
                while (true){
                    System.out.println("Enter the number code: ");
                    if(input.hasNextInt()){
                        num = input.nextInt();
                        if (num <0 || num > 12){
                            System.out.println("Invalid number code! Please try again.");
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        System.out.println("Invalid number code! Please try again.");
                        input.next();
                    }
                }

                switch (num) {
                    case 1 -> west.addDocInfo(input);
                    case 2 -> west.deleteDocInfo(input);
                    case 3 -> west.printDocInfo();
                    case 4 -> west.saveDocInfo();
                    case 5 -> west.loadDocInfo();
                    case 6 -> west.savePatientInfo();
                    case 7 -> west.loadPatientInfo();
                    case 8 -> west.saveConsultationInfo();
                    case 9 -> west.loadConsultationInfo();
                    case 10 -> west.openGUI();
                    case 11 -> System.out.println("Thank you cooperating with us!");
                    default -> System.out.println("Please enter one of the numbers above");
                }
            }
            while (num != 11);
        }
        catch (InputMismatchException e){
            System.out.println("Invalid Input! Please try again.");
        }

    }
}