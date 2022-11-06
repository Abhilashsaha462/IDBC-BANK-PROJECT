import java.sql.*;
import java.util.Scanner;

public class connection
{
    public Connection Account_con() throws SQLException
    {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banking1","root","Sahasiem7#");
        return con;
    }

    public void insert(Details AC) throws SQLException
    {
        connection co=new connection();
        Connection con=co.Account_con();
        String query1="insert into Account values(?,?,?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(query1);
        pst.setString(1,AC.getAccount_number());
        pst.setObject(2,String.valueOf(AC.getType()));
        pst.setInt(3,AC.getAge());
        pst.setString(4,AC.getName());
        pst.setString(5,AC.getAddress());
        pst.setString(6, AC.getMobNo());
        pst.setDouble(7,AC.getBalance());
        pst.executeUpdate();
        System.out.println("Account Created Successfully");
        String s="New account added";
        co.Insert_to_log(AC,s,AC.getBalance());
    }

    public void Insert_to_log(Details ac,String remark,double amount)throws SQLException
    {
        connection co=new connection();
        Connection con=co.Account_con();
        String query2="insert into Log(Account_Number,Remark,Balance,Amount) values(?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(query2);
        pst.setString(1,ac.getAccount_number());
        pst.setString(2,remark);
        pst.setString(3,String.valueOf(amount));
        pst.setDouble(4,ac.getBalance());
        pst.execute();
    }
    public Details Accountpresent(String Account_Number) throws SQLException {
        connection co=new connection();
        Connection con=co.Account_con();
        String query3="select * from Account where Account_number=?";
        PreparedStatement pst1=con.prepareStatement(query3);
        pst1.setString(1,Account_Number);
        ResultSet rs=pst1.executeQuery();
        rs.next();
        Details d = new Details(rs.getString(1), Account_Type.valueOf(rs.getString(2)), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getLong(7));
        if(d==null)
        {
            System.out.printf("Wrong Account Number");
            return null;
        }
        return d;
    }

    public void deposit_Amount(Details ac, double amount, String remark)throws SQLException
    {
        connection co=new connection();
        Connection con=co.Account_con();
        String query4="update Account set Balance=? where Account_number=?";
        PreparedStatement pst2=con.prepareStatement(query4);
        pst2.setDouble(1,ac.getBalance());
        pst2.setString(2,ac.getAccount_number());
        pst2.executeUpdate();
        co.Insert_to_log(ac,remark,amount);
    }

    public void Display_Account(Details ac)throws SQLException
    {
        connection co=new connection();
        Details d=co.Accountpresent(ac.getAccount_number());
        String s="Displayed Details";
        co.Insert_to_log(ac,s,0.0);
        System.out.println(d);
    }

    public void change_balance(Details ac,String remark, Double amount)throws SQLException
    {
        connection co=new connection();
        co.deposit_Amount(ac,amount,remark);
    }

    public void Display_Log(String Ac_No)throws SQLException
    {
        connection co=new connection();
        Connection con=co.Account_con();
        String query5="select * from Log where Account_Number=?";
        PreparedStatement pst3=con.prepareStatement(query5);
        pst3.setString(1,Ac_No);
        ResultSet rs1=pst3.executeQuery();
        System.out.format("Sl_no| %20s | %20s | %50s | %15s | %15s | \n","Account Number","Date and Time","Remark","Balance","Amount");
        while (rs1.next())
        {
            System.out.format("  | %20s | %20s | %50s | %15s | %15s | \n"+rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getInt(6));
        }
    }

    public String Transaction(Details d,String pay_ac)throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        connection co=new connection();
        Details pay=co.Accountpresent(pay_ac);
        System.out.println("Name of the Account Holder is \n"+pay.getName()+"\nDo You Want to Continue\nPress y for yes\nPress N for NO");
        String in=sc.nextLine();
        if(in.equalsIgnoreCase("n"))
        {
            return "";
        }
        else if (in.equalsIgnoreCase("y")) {
            System.out.println("Enter the amount want To transfer");
            double amount=sc.nextDouble();
            double bal=d.getBalance()-amount;
            if(bal>=1) {
                pay.setBalance(pay.getBalance() + amount);
                String s = ("Amount transfer to " + pay.getAccount_number() + " ");
                co.change_balance(d, s, amount);
                String ss = ("Amount transfer by " + d.getAccount_number() + " ");
                co.change_balance(pay, s, amount);
            }
            else if(bal<1)
            {
                System.out.println("Insufficient fund");
            }
        }
        return "Done";
    }

    public boolean MobNo(String MobNo)throws SQLException {
        connection con=new connection();
        Connection co=con.Account_con();
        String query6="Select MobNo from account where MobNo=?";
        PreparedStatement pst4=co.prepareStatement(query6);
        pst4.setString(1,MobNo);
        ResultSet rs2=pst4.executeQuery();
        if(rs2.next())
        {
            System.out.println("Mobno Exist With other person. Please Try with other number");
            return false;
        }
        return true;
    }
}

