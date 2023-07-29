package ATMINTERFACE;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ATM extends JFrame{
    private BankAccount bankAccount;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton WithdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;

    public ATM(BankAccount  bankAccount)
    {
        this.bankAccount = bankAccount;
        setTitle("ATM");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        balanceLabel = new JLabel("Enter Amount \n");
        add(balanceLabel);

        amountField = new JTextField(20);
        add(amountField);

        WithdrawButton = new JButton("Withdraw");
        WithdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                Withdraw(amount);
                amountField.setText(" ");
            }
        });
        add(WithdrawButton);

        depositButton =new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount =Double.parseDouble(amountField.getText());
                deposit(amount);
                amountField.setText("");
            }
        });
        add(depositButton);

        checkBalanceButton = new JButton("check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        add(checkBalanceButton);

        add(new JPanel());
        setVisible(true);
    }
    private void Withdraw(double amount)
    {
        if(amount<=500)
        {
            showMessage("Invalid amount. please enter a positive value.");
            return;
        }
        if(amount>bankAccount.getBalance()){
            showMessage("Insufficient funds.");
            return;
        }
        bankAccount.withdraw(amount);
        updateBalanceLabel();
        showMessage("Withdrawal successful.");
    }

    private void deposit(double amount){
        if(amount<=0)
        {
            showMessage("Invlid amount. please enter a positive value.");
            return;
        }
        bankAccount.deposit(amount);
        updateBalanceLabel();
        showMessage("Deposit Successful.");
    }
    private void checkBalance(){
        showMessage("Your balance is : $"+ bankAccount.getBalance());
    }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this,message);
    }
    private void updateBalanceLabel(){
        balanceLabel.setText("Enter Amount \n");
    }
    public static void main(String args[]) {
        BankAccount bankAccount = new BankAccount(1000.0);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATM(bankAccount);
            }
        });
    }
}
class BankAccount{
    private double balance;
    public BankAccount(double balance){
        this.balance =  balance;
    }
    public double getBalance(){
        return balance;
    }

    public void withdraw(double amount){
        balance-=amount;
    }

    public void deposit(double amount){
        balance+=amount;
    }
}