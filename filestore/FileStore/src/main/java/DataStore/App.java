package DataStore;

import Controller.PersonController;
import Model.PersonStoreModel;
import View.PersonView;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by jeff on 4/27/2016.
 */
public class App {


    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        PersonView view = new PersonView();
        PersonDataStoreFile pdsf = new PersonDataStoreFile(new RSAKey());
        PersonStoreModel ps = new PersonStoreModel(pdsf);
        PersonController controller = new PersonController(view,ps);

        System.out.println("[1] List Person");
        System.out.println("[2] Add Person");
        System.out.println("[3] Search Person");
        System.out.println("Select Operation: ");


        try{
            int input = scan.nextInt();

            if (input == 1){

                controller.getAllRecord();

            }else if (input == 2){

                Scanner scanFname = new Scanner(System.in);
                Scanner scanLname = new Scanner(System.in);
                Scanner scanId = new Scanner(System.in);

                System.out.println("Enter ID");
                int id = scanId.nextInt();
                System.out.println("Enter Firstname");
                String firstName = scanFname.nextLine();
                System.out.println("Enter Lastname");
                String lastName = scanLname.nextLine();


                controller.add(new Person(false,id,firstName,lastName));

            }else if(input == 3){

                System.out.println("Enter ID");
                Scanner scanId = new Scanner(System.in);
                int id = scanId.nextInt();

                controller.search(id);

            }

        }catch (InputMismatchException ex){

            System.out.print("Please select from 1 2 3");
        }

    }
}
