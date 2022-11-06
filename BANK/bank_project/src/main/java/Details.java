public class Details
{
    private String account_number;
    private Account_Type type;
    private int Age;
    private String Name;
    private String Address;
    private String MobNo;
    private double balance;

    public Details(String account_number, Account_Type type, int age, String name, String address, String mobNo, double balance) {
        this.account_number = account_number;
        this.type = type;
        this.Age = age;
        this.Name = name;
        this.Address = address;
        this.MobNo = mobNo;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public Account_Type getType() {
        return type;
    }

    public void setType(Account_Type type) {
        this.type = type;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobNo() {
        return MobNo;
    }

    public void setMobNo(String mobNo) {
        MobNo = mobNo;
    }

    @Override
    public String toString()
    {
        return "\nAccount_number = " + account_number  +
                "\n Account Type = " + type +
                "\n Age = " + Age +
                "\n Account Holder Name='" + Name  +
                "\n Address = " + Address +
                "\n Phone = " + MobNo +
                "\n balance = " + balance ;
    }
}