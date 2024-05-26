package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String userType;
    private List<String> familyMembers;
    private String currency;
    private double balance; // 用户余额
    private List<Transaction> transactions; // 交易记录

    private double maxDepositLimit = Double.MAX_VALUE; // 默认无上限
    private double maxWithdrawLimit = Double.MAX_VALUE; // 默认无上限
    private boolean isLocked = false; // 默认未锁定

    // 无参数构造器
    public User() {
        this.familyMembers = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public User(String username, String password, String userType, List<String> familyMembers, String currency, double initialBalance) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.familyMembers = familyMembers;
        this.currency = currency;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<String> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<String> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getMaxDepositLimit() {
        return maxDepositLimit;
    }

    public void setMaxDepositLimit(double maxDepositLimit) {
        this.maxDepositLimit = maxDepositLimit;
    }

    public double getMaxWithdrawLimit() {
        return maxWithdrawLimit;
    }

    public void setMaxWithdrawLimit(double maxWithdrawLimit) {
        this.maxWithdrawLimit = maxWithdrawLimit;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    // 加载用户数据
    public static List<User> loadUsers(String path) {
        List<User> users = new ArrayList<>();
        try {
            String data = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                List<String> familyMembers = new ArrayList<>();
                JSONArray membersJson = json.optJSONArray("familyMembers");
                if (membersJson != null) {
                    for (int j = 0; j < membersJson.length(); j++) {
                        familyMembers.add(membersJson.getString(j));
                    }
                }
                User user = new User(
                        json.getString("username"),
                        json.getString("password"),
                        json.getString("userType"),
                        familyMembers,
                        json.getString("currency"),
                        json.getDouble("balance")
                );
                if (json.has("maxDepositLimit")) {
                    user.setMaxDepositLimit(json.getDouble("maxDepositLimit"));
                }
                if (json.has("maxWithdrawLimit")) {
                    user.setMaxWithdrawLimit(json.getDouble("maxWithdrawLimit"));
                }
                if (json.has("isLocked")) {
                    user.setLocked(json.getBoolean("isLocked"));
                }
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading user data: " + e.getMessage());
        }
        return users;
    }

    // 验证用户的凭证
    public static User validate(String username, String password) {
        try {
            String path = "users.json";
            String data = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userObj = jsonArray.getJSONObject(i);
                if (userObj.getString("username").equals(username) && userObj.getString("password").equals(password)) {
                    List<String> familyMembers = new ArrayList<>();
                    JSONArray membersJson = userObj.optJSONArray("familyMembers");
                    if (membersJson != null) {
                        for (int j = 0; j < membersJson.length(); j++) {
                            familyMembers.add(membersJson.getString(j));
                        }
                    }
                    User user = new User(
                            userObj.getString("username"),
                            userObj.getString("password"),
                            userObj.getString("userType"),
                            familyMembers,
                            userObj.getString("currency"),
                            userObj.getDouble("balance")
                    );
                    if (userObj.has("maxDepositLimit")) {
                        user.setMaxDepositLimit(userObj.getDouble("maxDepositLimit"));
                    }
                    if (userObj.has("maxWithdrawLimit")) {
                        user.setMaxWithdrawLimit(userObj.getDouble("maxWithdrawLimit"));
                    }
                    if (userObj.has("isLocked")) {
                        user.setLocked(userObj.getBoolean("isLocked"));
                    }
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 注册用户
    public static boolean registerUser(String path, User newUser) {
        try {
            File file = new File(path);
            JSONArray usersArray;

            if (file.exists() && file.length() != 0) {
                String data = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
                usersArray = new JSONArray(data);
            } else {
                usersArray = new JSONArray();
            }

            JSONObject newUserJson = new JSONObject();
            newUserJson.put("username", newUser.username);
            newUserJson.put("password", newUser.password);
            newUserJson.put("userType", newUser.userType);
            newUserJson.put("familyMembers", new JSONArray(newUser.familyMembers));
            newUserJson.put("currency", newUser.currency);
            newUserJson.put("balance", newUser.balance);
            newUserJson.put("transactions", new JSONArray(newUser.transactions));
            if (newUser.userType.equals("Child")) {
                newUserJson.put("maxDepositLimit", newUser.maxDepositLimit);
                newUserJson.put("maxWithdrawLimit", newUser.maxWithdrawLimit);
                newUserJson.put("isLocked", newUser.isLocked);
            }

            usersArray.put(newUserJson);
            Files.write(Paths.get(path), usersArray.toString().getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 存款
    public void deposit(double amount, String type, String duration) throws IOException {
        if (this.isLocked) {
            throw new IllegalStateException("Account is locked.");
        }

        if (amount > maxDepositLimit) {
            throw new IllegalArgumentException("Deposit amount exceeds the maximum deposit limit.");
        }

        if ("time deposit".equals(type) && !duration.isEmpty()) {
            try {
                int lockDays = Integer.parseInt(duration);
                LocalDateTime lockUntil = LocalDateTime.now().plusSeconds(lockDays);
                this.transactions.add(new Transaction("Deposit", amount, type, lockUntil));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The period of time deposit must be a number!");
            }
        } else {
            this.transactions.add(new Transaction("Deposit", amount, type, null));
        }
        this.balance += amount;

        updateUserData();
    }

    // 取款
    public void withdraw(double amount, String type) throws Exception {
        if (this.isLocked) {
            throw new IllegalStateException("Account is locked.");
        }

        if (amount > maxWithdrawLimit) {
            throw new IllegalArgumentException("Withdrawal amount exceeds the maximum withdrawal limit.");
        }

        if (this.balance >= amount) {
            for (Transaction transaction : this.transactions) {
                if (transaction.getType().equals("Deposit") && "time deposit".equals(type)) {
                    if (transaction.getLockUntil() != null && LocalDateTime.now().isBefore(transaction.getLockUntil())) {
                        throw new Exception("You can't withdraw money during the time deposit period");
                    }
                }
            }
            this.balance -= amount;
            this.transactions.add(new Transaction("Withdrawal", amount, null, null));

            updateUserData();
        } else {
            throw new Exception("Insufficient funds");
        }
    }

    private void updateUserData() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("users.json")), StandardCharsets.UTF_8);
        JSONArray usersArray = new JSONArray(data);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.getJSONObject(i);
            if (user.getString("username").equals(this.username)) {
                JSONArray transactionsJsonArray = new JSONArray();
                for (Transaction transaction : this.transactions) {
                    JSONObject transactionJson = new JSONObject();
                    transactionJson.put("type", transaction.getType());
                    transactionJson.put("amount", transaction.getAmount());
                    transactionJson.put("timestamp", transaction.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    if (transaction.getDuration() != null) {
                        transactionJson.put("duration", transaction.getDuration());
                    } else {
                        transactionJson.put("duration", JSONObject.NULL);
                    }
                    if (transaction.getLockUntil() != null) {
                        transactionJson.put("lockUntil", transaction.getLockUntil().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    } else {
                        transactionJson.put("lockUntil", JSONObject.NULL);
                    }
                    transactionsJsonArray.put(transactionJson);
                }
                user.put("transactions", transactionsJsonArray);
                user.put("balance", this.balance);
                break;
            }
        }
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes(StandardCharsets.UTF_8));
    }
}
