import  java.util.*;
import java.io.Console;

public class Register {

    //Function to validate password
    public static boolean isValidPassword(String regupwd) {
       try{
            boolean hasLetter = false;
            boolean hasUpperCase = false;
            boolean hasLowerCase = false;
            boolean hasDigit = false;

            for (int i = 0; i < regupwd.length(); i++) {
                char c = regupwd.charAt(i);

                //checking if character is letter
                if (Character.isLetter(c)) {
                    hasLetter = true;
                }

                //checking if character is uppercase
                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                }

                if (Character.isLowerCase(c)) {
                    hasLowerCase = true;
                }
                //checking if character is Number
                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
            }
            return hasLetter && hasUpperCase && hasLowerCase && hasDigit && regupwd.length() >= 6;

        }catch(Exception e){
            System.out.println("Something went wrong"+e);
             return false;        
        }
    }

    // Is registering username is already taken by someone else or not
    private static boolean isUsernameTaken(
            ArrayList<User> registeredUsers,
            String username
    ) {
        for (User usr : registeredUsers) {
            if (usr.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public static void userRegister(ArrayList<User> registeredUsers){
        Console cnl=System.console();
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------ Registration ---------------------------------");

        System.out.println("Enter Full name : ");
        String regufullname = sc.nextLine();
        //Full Name length Validation
        while (regufullname.length() < 1) {
                System.out.println("-----------------------------------");
                System.out.println("| Error: Please Enter the Name    |");
                System.out.println("-----------------------------------");
                System.out.print("Enter Full name: ");
                regufullname = sc.nextLine();
            }
        System.out.println("Enter Username : ");
        String reguuname = sc.nextLine();
        //Username length Validation
            while (reguuname.length() < 6) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("| Error: Name must be at least 6 characters long and only alphabet.   |");
                System.out.println("-----------------------------------------------------------------------");
                System.out.print("Enter Username : ");
                reguuname = sc.nextLine();
            }
            
        if (isUsernameTaken(registeredUsers, regufullname)) {
            System.out.println(
                    "Username is already taken, please try again with a different username"
            );
        } else {
            // System.out.println("Enter Password : ");
            // String regupwd = sc.nextLine();
            char[] regupwd = cnl.readPassword("Enter your password:");
            System.out.println("-------------------------");

            System.out.println();
            //Password Validation
            while (!isValidPassword( new String(regupwd))){
                System.out.println("-------------------------------------------------------------------------------------------------------");
                System.out.println("Password must contain 6 characters long, one lowercase letter, one uppercase letter, and one digit.    ");
                System.out.println("-------------------------------------------------------------------------------------------------------");
                regupwd = cnl.readPassword("Enter your password:");
                System.out.println("-----------------------------------------------------------------------------------");
            }

            User newuser = new User(regufullname, reguuname, new String(regupwd));
            registeredUsers.add(newuser);
            System.out.println("User Successfully registered.........");
            System.out.println("-----------------------------------------------------------------------------------");
        }

    }
}
