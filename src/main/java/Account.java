import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private static class Transaction {
        private final LocalDate date;
        private final int amount;

        public Transaction(LocalDate date, int amount) {
            this.date = date;
            this.amount = amount;
        }

        public LocalDate getDate() {
            return date;
        }

        public int getAmount() {
            return amount;
        }
    }

    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;


    @Override
    public void deposit(int amount, LocalDate date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(date, amount));
    }


    @Override
    public void withdraw(int amount, LocalDate date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
        transactions.add(new Transaction(date, -amount));
    }


    @Override
    public String printStatement() {
        StringBuilder statement = new StringBuilder();
        String lineSeparator = "\n";
        statement.append("Date       || Amount || Balance").append(lineSeparator);
        int runningBalance = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Transaction transaction : transactions) {
            runningBalance += transaction.getAmount();
            String formattedDate = transaction.getDate().format(formatter);
            statement.append(String.format("%s || %d || %d%s", formattedDate, transaction.getAmount(), runningBalance, lineSeparator));
        }
        return statement.toString();
    }


}
