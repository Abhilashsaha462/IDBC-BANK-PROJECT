import java.sql.SQLException;
import java.util.Scanner;

public class Implementation
{
    public static void main(String[] arg) throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("WelCome To IDBC Bank");
        while (true)
        {
            System.out.printf("Press 1  New Customer \n" +
                    "Press 2 Existing Customer\n");
            int Types = sc.nextInt();
            connection con = new connection();
            switch (Types)
            {
                case 1:
                {
                    Account tc = new Account();
                    Details Account = tc.Type_of_Account();
                    Methods m = new Methods();
                    String s=m.account_methods(Account);
                    break;
                }
                case 2:
                {
                    System.out.println("Enter the Account Number");
                    boolean t = true;
                    sc.nextLine();
                    String Ac_no = sc.nextLine();
                    while (t)
                    {
                        Details ac = con.Accountpresent(Ac_no);
                        if (ac == null)
                        {
                            System.out.println("Enter Again");
                            Ac_no = sc.nextLine();
                            ac=con.Accountpresent(Ac_no);
                        }
                        else
                        {
                            t = false;
                        }
                        System.out.println("===========Details==========");
                        System.out.println(ac);
                        System.out.println();
                        Methods m = new Methods();
                        String s=m.account_methods(ac);
                    }
                    break;
                }
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}