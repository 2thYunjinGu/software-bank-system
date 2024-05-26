package Model;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    private String duration;
    private LocalDateTime lockUntil;

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLockUntil(LocalDateTime lockUntil) {
        this.lockUntil = lockUntil;
    }

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

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", duration='" + duration + '\'' +
                ", lockUntil=" + lockUntil +
                '}';
    }

    public String getSubType() {
        return this.type.equals("Deposit") ? "time deposit" : "current deposit";
    }

    // 新增的方法
    public long getRemainingTimeInSeconds() {
        if (lockUntil == null) {
            return 0;
        }
        return LocalDateTime.now().until(lockUntil, ChronoUnit.SECONDS);
    }
}
