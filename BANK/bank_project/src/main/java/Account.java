import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Account {
    public Details Type_of_Account()throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("For 'SAVE' Account Press S\nFor 'Pay' Account Press P");
        String type=sc.nextLine();
        Boolean t=true;
        Account_Type ty=null;
        if (type.equalsIgnoreCase("S") || type.equalsIgnoreCase("Save"))
        {
            ty=Account_Type.Save_Account;
        }
        else if (type.equalsIgnoreCase("P") || type.equalsIgnoreCase("Pay"))
        {
            ty=Account_Type.Pay_Account;
        }
        System.out.println("Enter your Name");
        String name=sc.nextLine();
        System.out.println("Enter your Mobile Number");
        String Mobno=sc.nextLine();
        int age=0;
        System.out.println("Enter your Age");
        age=sc.nextInt();
        while( age<18 || age>75)
        {
            if(age<18)
            {
                System.out.println("Age should be above 18\nEnter age again");
                age=sc.nextInt();
            }
            else if (age>75)
            {
                System.out.println("Age should Be under 75\nEnter Age again");
                age=sc.nextInt();
            }
        }
        System.out.println("Enter your Address");
        sc.nextLine();
        String address=sc.nextLine();
        String chars = "0123456789";
        int len=12;
        Random rnd = new Random();
        String sb="";
        for (int i = 0; i < len; i++)
            sb=sb+(chars.charAt(rnd.nextInt(chars.length())));
        System.out.println("Enter the Amount you want to deposit");
        double bal=sc.nextInt();
        Details d= new Details(sb,ty, age, name, address, Mobno,bal) ;
        connection con=new connection();
        con.insert(d);
        System.out.println("\n\nYour Account Details Are Provided");
        System.out.println(d);
        return d;
    }
}
