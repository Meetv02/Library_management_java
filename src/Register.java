import java.util.ArrayList;
import java.util.Scanner;

public class Register {
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
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------ Registration ---------------------------------");

        System.out.println("Enter Full name : ");
        String regufullname = sc.nextLine();
        System.out.println("Enter Username : ");
        String reguuname = sc.nextLine();

        if (isUsernameTaken(registeredUsers, regufullname)) {
            System.out.println(
                    "Username is already taken, please try again with a different username"
            );
        } else {
            System.out.println("Enter Password : ");
            String regupwd = sc.nextLine();

            User newuser = new User(regufullname, reguuname, regupwd);
            registeredUsers.add(newuser);
            System.out.println("User Successfully registered.........");
        }

    }
}
