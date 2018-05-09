/**
 * Account
 */
public class Account {

    public Account() {

    }

    public Account(double balance) {
        this.balance = balance;
    }

    private double balance;

	public double getBalance() {
		return balance;
	}

    public void deposit(double amt) {
        this.balance += amt;
    }

    public void withdraw(double amt) throws OverdraftExcetpion {
        if (amt > this.balance) {
            throw new OverdraftExcetpion("余额不足", amt - this.balance);
        } else {
            this.balance -= amt;
        }
    }
}

class OverdraftExcetpion extends Exception {

    private static final long serialVersionUID = 1L;
    
    private double deficit;

	public OverdraftExcetpion(String msg, double deficit) {
        super(msg);
        this.deficit = deficit;
    }

	public double getDeficit() {
		return deficit;
	}

}