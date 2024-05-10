package Model;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.File;
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

    // 带参数的构造器

    // 加载用户数据
    public static List<User> loadUsers(String path) {
        List<User> users = new ArrayList<>();
        try {
            String data = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(data);  // 使用 JSONArray 来解析

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                List<String> familyMembers = new ArrayList<>();
                JSONArray membersJson = json.optJSONArray("familyMembers");
                if (membersJson != null) {
                    for (int j = 0; j < membersJson.length(); j++) {
                        familyMembers.add(membersJson.getString(j));
                    }
                }
                users.add(new User(
                        json.getString("username"),
                        json.getString("password"),
                        json.getString("userType"),
                        familyMembers,
                        json.getString("currency"),
                        json.getDouble("balance")
                ));
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
            String path = "users.json";  // JSON文件的路径
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
                    return new User(
                            userObj.getString("username"),
                            userObj.getString("password"),
                            userObj.getString("userType"),
                            familyMembers,
                            userObj.getString("currency"),
                            userObj.getDouble("balance")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // 如果没有找到匹配的用户，返回null
    }

    public static boolean registerUser(String path, User newUser) {
        try {
            File file = new File(path);
            JSONArray usersArray;

            // 检查文件是否存在并且不为空
            if (file.exists() && file.length() != 0) {
                String data = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
                usersArray = new JSONArray(data);  // 加载现有用户数组
            } else {
                usersArray = new JSONArray();  // 创建一个新的JSON数组，如果文件不存在或为空
            }

            JSONObject newUserJson = new JSONObject();
            newUserJson.put("username", newUser.username);
            newUserJson.put("password", newUser.password);
            newUserJson.put("userType", newUser.userType);
            newUserJson.put("familyMembers", new JSONArray(newUser.familyMembers));
            newUserJson.put("currency", newUser.currency);
            newUserJson.put("balance", newUser.balance);

            // 将新用户添加到数组
            usersArray.put(newUserJson);

            // 将更新后的数组写回文件
            Files.write(Paths.get(path), usersArray.toString().getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 存款
    public void deposit(double amount, String type, String duration) {
        if ("time deposit".equals(type) && !duration.isEmpty()) {
            try {
                int lockDays = Integer.parseInt(duration);
                // 正确处理锁定时间
                LocalDateTime lockUntil = LocalDateTime.now().plusSeconds(lockDays);  // 如果一秒代表一天，则应使用plusDays
                this.transactions.add(new Transaction("Deposit", amount, type, lockUntil));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The period of time deposit must be a number!");
            }
        } else {
            // 对于活期存款，没有锁定时间
            this.transactions.add(new Transaction("Deposit", amount, type, null)); // 使用null代表没有锁定时间
        }
        this.balance += amount;  // 更新余额
    }




    // 取款
    public void withdraw(double amount, String type) throws Exception {
        // 检查是否有足够的余额和是否满足time deposit存款的锁定期条件
        if (this.balance >= amount) {
            // 检查所有存款交易以确保没有锁定期未到的time deposit存款
            for (Transaction transaction : this.transactions) {
                if (transaction.getType().equals("Deposit") && "Time deposit".equals(type)) {
                    if (transaction.getLockUntil() != null && LocalDateTime.now().isBefore(transaction.getLockUntil())) {
                        throw new Exception("You can't withdraw money during the time deposit period"); //time deposit存款的锁定期尚未结束，不能取款
                    }
                }
            }

            this.balance -= amount;  // 扣减金额
            this.transactions.add(new Transaction("Withdrawal", amount, null, null));  // 记录交易
        } else {
            throw new Exception("Insufficient funds");
        }
    }

    // 获取余额
    public double getBalance() {
        return this.balance;
    }

    // 获取交易记录
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword(){
        return password;
    }

    public String getCurrency() {
        return currency;
    }
}

// Transaction class (already provided in the previous response)
