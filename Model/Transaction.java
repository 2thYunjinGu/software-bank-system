package Model;
import java.time.LocalDateTime;
public class Transaction {
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    private String duration;
    private LocalDateTime lockUntil;  

    public Transaction(String type, double amount, String duration, LocalDateTime lockUntil) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.duration = duration;
        this.lockUntil = lockUntil;
    }

    // Getter方法
    public LocalDateTime getLockUntil() {
        return lockUntil;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
