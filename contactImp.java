import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.InputMismatchException;
import java.nio.file.Path;
import java.nio.file.Files;



public class contactImp extends contactInterface {
    Scanner scan = new Scanner(System.in);
    Scanner scan2 = new Scanner(System.in);
    Scanner scan3 = new Scanner(System.in);

    File file = new File("contacts.txt");

    public void createFile() {

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File " + file.getName() + " has been created ");

            } catch (IOException ee) {
                ee.printStackTrace();
            }
        } else {
            System.out.println("");
            System.out.println(" :) adding to file ;) ");

        }
    }

    public void addToContact()  {

        createFile();
        System.out.println(" ");
        System.out.println("----Add to contact section-----");
        System.out.println("");
        System.out.print("Input Contact's First Name : ");
        String nameInput = scan.next();
        System.out.print("Input Contacts PhoneNo     :");
        long uniquePhoneNo = 0;
        try{
            uniquePhoneNo = makingPhoneNoUnquie();
        }catch (InputMismatchException e){
            System.err.println("error try numbers less than 10 next time");

        }
        System.out.print("Input Contact's Gender     : ");
        String genderInput = scan2.next();
        System.out.print("Input Contact's Email      : ");
        String emailInput = scan3.next();
        String details = nameInput + ">#<" + uniquePhoneNo+ ">#<"+ genderInput + ">#<" + emailInput+"\n";
        System.out.println(" ");

        try {
            FileWriter fwrite = new FileWriter(file,true);
            fwrite.write(details);
            System.out.println("written suucessfully");
            fwrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer makingPhoneNoUnquie()  {


        Integer phoneNoInput = scan.nextInt();
        String lines;
        String [] details ;
        boolean unqiue = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((lines = reader.readLine())!=null){
                details = lines.split(">#<");
                String strNo = String.valueOf(phoneNoInput);


                   if(strNo.length()==details[1].length()) {
                       if(strNo.contains(details[1])){
                           unqiue = true;
                       }

                   }



            }

        }catch (IOException io){

        }


        if(unqiue==true){
            System.err.println("sorry no taken");
            addToContact();
            showMenu();

        }


        return phoneNoInput;
    }

    public void viewContact()  {
        String lines;
        String[] details;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

                while((lines = reader.readLine())!=null){
                    details = lines.split(">#<");
                    System.out.println("First Name    :"+ details[0]);
                    System.out.println("Phone Number  :"+ details[1]);
                    System.out.println("Gender        :"+ details[2]);
                    System.out.println("Email         :"+ details[3]);
                    System.out.println(" ");

                }
            reader.close();

        }catch (IOException io){
            System.out.println("");
            System.out.println("Contact has been deleted");
            System.out.println("");
            System.out.println("Unable to read from from file "+io.getMessage());
            System.out.println("");
        }

    }

    public void search(){
        System.out.println("");
        System.out.println("======= To Search for a Contact =======");
        System.out.print("Input Contact Phone :");
        Integer searchInput = scan.nextInt();

        String [] details = new String[0];

        List<String> fileContent = null;

             String strNo = String.valueOf(searchInput);


            try {

                fileContent = new ArrayList<>(Files.readAllLines(Path.of("contacts.txt"), StandardCharsets.UTF_8));
                for (int i = 0; i < fileContent.size(); i++) {

                  String[] eachWord =  fileContent.get(i).split(">#<");

                    if(eachWord[1].length()==strNo.length()){
                        if (eachWord[1].contains(strNo)) {


                            //delete the line from the list
                            System.out.println("First Name    : "+eachWord[0]);
                            System.out.println("Phone Number  : "+eachWord[1]);
                            System.out.println("Gender        : "+eachWord[2]);
                            System.out.println("Email         : "+eachWord[3]);
                            System.out.println(" ");

                        }
                    }


                }


            }catch (IOException io){

            }




    }

    public void update(){
        System.out.println(" ");
        System.out.println("------- Update Section -------");
        System.out.println("");
        System.out.println(" Pls Choose From The Following ");
        System.out.println(">>>>  1: Change a Contact's First Name    <<<<<");
        System.out.println(">>>>  2: Change a Contact's Gender        <<<<<");
        System.out.println(">>>>  3: Change a Contact's Email         <<<<<");
        System.out.println(">>>>  4: Back                             <<<<<");

        int updateInput = scan.nextInt();

        switch (updateInput){
            case 1:
                editName();
                break;
            case 2:editGender();
                break;
            case 3:
                editEmail();
                break;
            case 4:
                showMenu();
                break;
            default:
                System.err.println("Wrong input....");
                showMenu();
        }


    }
    public void editName(){
        System.out.println("");
        System.out.println("======= Edit Contact's First Name  =======");
        System.out.print("Input Contact Phone : ");
        Integer editNameInput = scan3.nextInt();
        System.out.println(" ");
        String notFound = "";

        String oldName = "";
        String newName = "";
        String  nameTOEditTo = "";
        String [] details = new String[0];

        List<String> fileContent = null;

        String strNo = String.valueOf(editNameInput);
        String[] eachWord = new String[0];


        try {

            fileContent = new ArrayList<>(Files.readAllLines(Path.of("contacts.txt"), StandardCharsets.UTF_8));


            for (int i = 0; i < fileContent.size(); i++) {

                eachWord =  fileContent.get(i).split(">#<");

                if(eachWord[1].length()==strNo.length()){
                    if (eachWord[1].contains(strNo)) {
                        oldName+=eachWord[0];


                        newName+= nameTOEditTo+">#<"+eachWord[1]+">#<"+eachWord[2]+">#<"+eachWord[3];
                        fileContent.remove(i);
                        notFound+="yep";

                    }
                    else {
                        notFound +="true";
                    }
                }else {
                    notFound+="true";
                }



            }

            if(notFound.contains("yep")){
                System.out.print("Change ' "+ oldName+" ' First name to   : ");
                nameTOEditTo = scan3.next();

                String[] newNameArr = newName.split(">#<");

                if(newNameArr[0]==""){
                    newNameArr[0]=nameTOEditTo;


                }
                String realName = newNameArr[0]+">#<"+newNameArr[1]+">#<"+newNameArr[2]+">#<"+newNameArr[3];

                fileContent.add(realName);



                Files.write(Path.of("contacts.txt"), fileContent, StandardCharsets.UTF_8);
                System.out.println(" ");
                System.out.println("    (^_^)Name edited  ");
                System.out.println(" ");
            }else{
                System.out.println(" ");
                System.err.println("Phone does not match any record");
                System.out.println(" ");
                System.out.println("");
                update();
                System.out.println("");
                System.out.println(" ");
            }



        }catch (IOException io){

        }


    }

    //

    public void editGender(){
        System.out.println("");
        System.out.println("======= Edit Contact's Gender  =======");
        System.out.print("Input Contact Phone : ");
        Integer editGenderInput = scan3.nextInt();
        System.out.println(" ");

        boolean notFound = false;
        String contactName = "";
        String oldGender = "";
        String newGender = "";
        String  genderTOEditTo = "";
        String [] details = new String[0];

        List<String> fileContent = null;

        String strNo = String.valueOf(editGenderInput);
        String[] eachWord = new String[0];


        try {

            fileContent = new ArrayList<>(Files.readAllLines(Path.of("contacts.txt"), StandardCharsets.UTF_8));


            for (int i = 0; i < fileContent.size(); i++) {

                eachWord =  fileContent.get(i).split(">#<");

                if(eachWord[1].length()==strNo.length()){
                    if (eachWord[1].contains(strNo)) {

                        contactName+=eachWord[0];
                        oldGender+=eachWord[2];


                        newGender+= eachWord[0]+">#<"+eachWord[1]+">#<"+genderTOEditTo+">#<"+eachWord[3];
                        fileContent.remove(i);

                    }
                }
                else {
                    notFound= true;
                }


            }

            if(notFound==true){
                System.out.print("Change "+contactName+"'s Gender ( "+oldGender+" ) to    : ");
                genderTOEditTo = scan3.next();

                String[] newGenderArr = newGender.split(">#<");

                if(newGenderArr[2]==""){
                    newGenderArr[2]=genderTOEditTo;


                }
                String realGender = newGenderArr[0]+">#<"+newGenderArr[1]+">#<"+newGenderArr[2]+">#<"+newGenderArr[3];

                fileContent.add(realGender);



                Files.write(Path.of("contacts.txt"), fileContent, StandardCharsets.UTF_8);
                System.out.println(" ");
                System.out.println("    (^_^)Name edited  ");
                System.out.println(" ");
            }else {
                System.out.println(" ");
                System.out.println(" ");
                System.err.println("Phone does not match any record");
                update();
            }


        }catch (IOException io){

        }

    }

    public void editEmail(){
        System.out.println("");
        System.out.println("======= Edit Contact's Email  =======");
        System.out.print("Input Contact Phone : ");
        Integer editEmailInput = scan3.nextInt();
        System.out.println(" ");

        boolean notFound = false;
        String contactName = "";
        String oldEmail = "";
        String newEmail = "";
        String  emailTOEditTo = "";
        String [] details = new String[0];

        List<String> fileContent = null;

        String strNo = String.valueOf(editEmailInput);
        String[] eachWord = new String[0];


        try {

            fileContent = new ArrayList<>(Files.readAllLines(Path.of("contacts.txt"), StandardCharsets.UTF_8));


            for (int i = 0; i < fileContent.size(); i++) {

                eachWord =  fileContent.get(i).split(">#<");

                if(eachWord[1].length()==strNo.length()){
                    if (eachWord[1].contains(strNo)) {
                        contactName+= eachWord[0];
                        oldEmail+=eachWord[3];


                        newEmail+= eachWord[0]+">#<"+eachWord[1]+">#<"+eachWord[2]+">#<"+emailTOEditTo+"!!!";
                        fileContent.remove(i);

                    }
                }
                else {
                    notFound= true;
                }


            }

            if(notFound==true){

                System.out.print("Change "+contactName+"'s Gender ( "+oldEmail+" ) to    : ");
                emailTOEditTo = scan3.next();


                String[] newEmailArr = newEmail.split(">#<");
                if(newEmailArr[3].equals("!!!")){
                    newEmailArr[3]=emailTOEditTo;


                }
                String realEmail = newEmailArr[0]+">#<"+newEmailArr[1]+">#<"+newEmailArr[2]+">#<"+newEmailArr[3];

                fileContent.add(realEmail);



                Files.write(Path.of("contacts.txt"), fileContent, StandardCharsets.UTF_8);
                System.out.println(" ");
                System.out.println("    (^_^)Name edited  ");
                System.out.println(" ");
            }
            else {
                System.out.println("");
                System.out.println(" ");
                System.err.println("Phone does not match any record");
                update();

            }


        }catch (IOException io){

        }

    }
    public void deleteSection(){
        System.out.println(" ");
        System.out.println("------- Delete Section -------");
        System.out.println("");
        System.out.println(" Pls Choose From The Following ");
        System.out.println(">>>>  1: Delete All Contact                <<<<<");
        System.out.println(">>>>  2: Delete A Contact                 <<<<<");
        System.out.println(">>>>  3: Back                             <<<<<");

        int updateInput = scan.nextInt();

        switch (updateInput){
            case 1:
                deleteFile();
                break;
            case 2:
                deleteAContact();
                break;
            case 3:
                showMenu();
                break;
            default:
                System.err.println("Wrong input....");
                showMenu();

        }

    }


    public void deleteFile(){
        try
        {
            if(file.exists()){

                if(file.delete())
                {
                    System.out.println("All Contacts Deleted successfully ");
                }
                else
                {
                    System.out.println("failed");
                }


           }

        }catch (Exception e){

        }


    }




    public void deleteAContact(){


        System.out.println("");
        System.out.println("======= To Delete a Contact =======");
        System.out.print("Input Contact Phone : ");
        Integer searchInput = scan2.nextInt();
        System.out.println(" ");


        String [] details = new String[0];

        List<String> fileContent = null;

        String strNo = String.valueOf(searchInput);


        try {

            fileContent = new ArrayList<>(Files.readAllLines(Path.of("contacts.txt"), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {

                String[] eachWord =  fileContent.get(i).split(">#<");

                if(eachWord[1].length()==strNo.length()){
                    if (eachWord[1].contains(strNo)) {
                        fileContent.remove(i);
                        System.out.println("");
                        System.out.println("Number Deleted");

                    }
                }



            }


            Files.write(Path.of("contacts.txt"), fileContent, StandardCharsets.UTF_8);

        }catch (IOException io){

        }


    }







    public void showMenu() {

        int input;
        do{
            System.out.println("-----Pls choose from the following------");
            System.out.println(">>>> 1. Add to Contact  <<<<");
            System.out.println(">>>> 2. display contact <<<<");
            System.out.println(">>>> 3. Search          <<<<");
            System.out.println(">>>> 4. Update          <<<<");
            System.out.println(">>>> 5. Delete          <<<<");
            System.out.println(">>>> 6. Exit            <<<<");

            input = scan.nextInt();

            switch (input){
                case 1:
                    addToContact();
                    break;
                case 2:
                    viewContact();
                    break;
                case 3:
                    search();
                    break;
                case 4 :
                    update();
                    break;
                case 5 :
                    deleteSection();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Wrong input....");
                    showMenu();


            }
        } while (input!=0);


    }


    }




