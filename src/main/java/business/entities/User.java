package business.entities;

import java.util.TreeMap;

public class User
{

    public User(String email, String password, String role, float balance)
    {
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.orders = new TreeMap<>();
    }

    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private String email;
    private String password; // Should be hashed and secured
    private String role;
    private float balance;
    private TreeMap<Integer,Order> orders;


    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public TreeMap<Integer, Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        orders.put(order.getId(),order);
    }
}
