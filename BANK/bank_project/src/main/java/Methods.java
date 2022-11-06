import java.sql.SQLException;
import java.util.Scanner;

public class Methods
{
    public String  account_methods(Details ac) throws SQLException
    {
        connection con = new connection();
        Scanner sc = new Scanner(System.in);
        if (ac.getType().toString().equalsIgnoreCase( Account_Type.Save_Account.toString()))
        {
            Boolean t=true;
            while (t)
            {
                //Interest is calculated on the available balance and credited into the
                //account on yearly basis, the interest rate for customers is 2.5%
                System.out.printf("\n1) Deposit amount by cash" +
                        "\n2) Deposit amount by cheque\n" +
                        "3) View Balance\n" +
                        "4) Withdraw \n" +
                        "5) Calculate Interest\n" +
                      //  "6) Fund Transfer\n" +
                        "6) Main menu\n" +
                        "7) Exit\n" +
                        "8) Display Log"+
                        "\n Enter your choice\t");
                int choice = sc.nextInt();
                switch (choice)
                {
                    case 1:
                    {
                        System.out.println("Enter the amount to Deposit by cash");
                        double amount = sc.nextDouble();
                        String remark = "Deposited the amount to Account through cash";
                        ac.setBalance(ac.getBalance()+amount);
                        con.deposit_Amount(ac, amount, remark);
                        System.out.println("Amount Deposited Successfully");
                        System.out.println("---------------------------------------------------------------");
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter the amount to Deposit by cheque");
                        double amount = sc.nextDouble();
                        String remark = "Deposited the amount to Account through Cheque";
                        ac.setBalance(ac.getBalance()+amount);
                        con.deposit_Amount(ac,ac.getBalance()+ amount, remark);
                        System.out.println("Amount Deposited Successfully");
                        System.out.println("-------------------------------------------------------------------");
                        break;
                    }
                    case 3:
                    {
                        con.Display_Account(ac);
                        System.out.println("--------------------------------------------------------------------");
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Enter the amount to Withdraw");
                        double amount = sc.nextDouble();
                        String remark = "Amount WithDrawn";
                        double bal=ac.getBalance()-amount;
                        if(bal<1)
                        {
                            System.out.println("Low Balance");
                        }
                        else
                        {
                            ac.setBalance(bal);
                        }
                        con.change_balance(ac, remark,amount);
                        System.out.printf("Amount Withdrawn Successfully");
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    }

                    case 5:
                    {
                        double interest = ac.getBalance() * 2.5 / 100;
                        System.out.println("Interest =" + interest);
                        ac.setBalance(ac.getBalance() + interest);
                        System.out.println();
                        String s = "Calculation of Interest on balance";
                        con.change_balance(ac, s, ac.getBalance());
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    }
                /*    case 6:
                      {
                        connection co = new connection();
                        // Transaction(Details d,String paye_ac,double amount)
                        System.out.println("Enter the account number you want to Transfer the money");
                        sc.nextLine();
                        String Payee = sc.nextLine();
                        String s=co.Transaction(ac, Payee);
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    } */
                    case 6:
                    {
                        t=false;
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    }
                    case 7:
                    {
                        System.exit(0);

                    }
                    case 8:
                    {
                        con.Display_Log(ac.getAccount_number());
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    }
                    default:
                        System.out.println("Invalid Input");
                }
            }
        }
        else if (ac.getType().equals(Account_Type.Pay_Account))
        {
            boolean t=true;
            while (t)
            {
                System.out.printf("\n1) Deposit amount by Cash" +
                                "\n2) Deposit amount by Cheque" +
                                "\n3) View Balance" +
                                "\n4) Withdraw" +
                              //  "\n6)Fund Transfer" +
                                "\n5) Main menu" +
                                "\n6) Exit" +
                                "\n7) Display Log" +
                        "\n Enter your choice\t");
                int choice1 = sc.nextInt();
                switch (choice1)
                {
                    case 1:
                    {
                        System.out.printf("Enter the amount to Deposit");
                        double amount = sc.nextDouble();
                        String remark = "Deposited the amount to Account through cash";
                        ac.setBalance(ac.getBalance()+amount);
                        con.deposit_Amount(ac, amount, remark);
                        System.out.printf("Amount Deposited Successfully");
                        System.out.println("----------------------------------------------------------------------");
                        break;

                    }
                    case 2:
                    {
                        System.out.printf("Enter the amount to Deposit by cheque");
                        double amount = sc.nextDouble();
                        String remark = "Deposited the amount to Account through Cheque";
                        ac.setBalance(ac.getBalance()+amount);
                        con.deposit_Amount(ac, amount, remark);
                        System.out.printf("Amount Deposited Successfully");
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    }
                    case 3:
                    {
                        con.Display_Account(ac);
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Enter the amount to Withdraw");
                        double bill = sc.nextDouble();
                        String s="Amount Withdrawn";
                        double bal=ac.getBalance()-bill;
                        if(bal<1)
                        {
                            System.out.println("Low Balance");
                        }
                        else
                        {
                            ac.setBalance(bal);
                        }
                        con.change_balance(ac,s,ac.getBalance());
                        System.out.println("----------------------------------------------------------------------");
                        break;
                    }

               /*     case 6:
                    {
                        connection co=new connection();
                        // Transaction(Details d,String paye_ac,double amount)
                        System.out.println("Enter the account number you want to Transfer the money");sc.nextLine();
                        String Payee=sc.nextLine();
                        co.Transaction(ac,Payee);
                    } */
                    case 5:
                    {
                        t=false;
                        break;
                    }
                    case 6:
                    {
                        System.exit(0);
                    }
                    case 7:
                    {
                        con.Display_Log(ac.getAccount_number());
                        break;
                    }
                    default:
                 //       System.out.println("Invalid input");
                }
            }
        }
        else
            System.out.printf("Invalid");
            return "";
    }
}