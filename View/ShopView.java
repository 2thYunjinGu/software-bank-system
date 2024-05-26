package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShopView {
    private JFrame frame;
    private JList<String> modelList;
    private JList<String> cartList;
    private JTextField modelNameField;
    private JTextField modelPriceField;
    private JTextField modelDescriptionField;
    private JButton addToCartButton;
    private JButton checkoutButton;
    private JButton addModelButton;
    private JLabel checkoutLabel;

    public ShopView() {
        frame = new JFrame("Bank Shop");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 400);
        frame.setLayout(null);

        modelList = new JList<>(new DefaultListModel<>());
        JScrollPane modelScrollPane = new JScrollPane(modelList);

        cartList = new JList<>(new DefaultListModel<>());
        JScrollPane cartScrollPane = new JScrollPane(cartList);

        JPanel goodsPanel = new JPanel();
        goodsPanel.setBounds(0, 0, 300, 200);
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(250, 0, 400, 200);
        JPanel cartPanel = new JPanel();
        cartPanel.setBounds(750, 0, 300, 200);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2));
        infoPanel.setBounds(250, 0, 400, 300);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBounds(250, 300, 400, 100);

        //Set the goods information
        JLabel modelNameLabel = new JLabel("Goods name: ");
        modelNameField = new JTextField();
        JLabel modelPriceLabel = new JLabel("Goods price: ");
        modelPriceField = new JTextField();
        JLabel modelDescriptionLabel = new JLabel("Goods description: ");
        modelDescriptionField = new JTextField();

        infoPanel.add(modelNameLabel);
        infoPanel.add(modelNameField);
        infoPanel.add(modelPriceLabel);
        infoPanel.add(modelPriceField);
        infoPanel.add(modelDescriptionLabel);
        infoPanel.add(modelDescriptionField);

        //We put the buttons to button_panel
        addToCartButton = new JButton("add to cart");
        checkoutButton = new JButton("check out");
        addModelButton = new JButton("add goods");
        buttonPanel.add(addModelButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(checkoutButton);

        //Set the center panel
        centerPanel.add(infoPanel);
        centerPanel.add(buttonPanel);

        //Set goods panel
        goodsPanel.add(modelScrollPane);
        cartPanel.add(cartScrollPane);

        frame.add(goodsPanel);
        frame.add(centerPanel);
        frame.add(cartPanel);
        frame.setVisible(true);

        // Adding a checkout label to display the total price during checkout
        checkoutLabel = new JLabel();
        checkoutLabel.setBounds(750, 200, 200, 50);
        frame.add(checkoutLabel);
    }

    public void addModel(String model) {
        DefaultListModel<String> modelListModel = (DefaultListModel<String>) modelList.getModel();
        modelListModel.addElement(model);
    }

    public void addToCart(String model) {
        DefaultListModel<String> cartListModel = (DefaultListModel<String>) cartList.getModel();
        cartListModel.addElement(model);
    }

    public int getModelListSelectedIndex() {
        return modelList.getSelectedIndex();
    }

    public List<String> getCartItems() {
        return cartList.getSelectedValuesList();
    }

    public String getModelName() {
        return modelNameField.getText();
    }

    public void setModelNameField(String name) {
        modelNameField.setText(name);
    }

    public double getModelPrice() {
        return Double.parseDouble(modelPriceField.getText());
    }

    public void setModelPriceField(double price) {
        modelPriceField.setText(String.valueOf(price));
    }

    public String getModelDescription() {
        return modelDescriptionField.getText();
    }

    public void setModelDescriptionField(String description) {
        modelDescriptionField.setText(description);
    }

    public void setAddToCartButtonListener(ActionListener listener) {
        addToCartButton.addActionListener(listener);
    }

    public void setCheckoutButtonListener(ActionListener listener) {
        checkoutButton.addActionListener(listener);
    }

    public void setAddModelButtonListener(ActionListener listener) {
        addModelButton.addActionListener(listener);
    }

    public void showCheckoutMessage(String message) {
        checkoutLabel.setText(message); // Using label instead of dialog for easier testing
    }

    public String getCheckoutMessage() {
        return checkoutLabel.getText(); // Added this method for testing purposes
    }

    // Added getters for buttons to simulate button clicks in tests
    public JButton getAddToCartButton() {
        return addToCartButton;
    }

    public JButton getCheckoutButton() {
        return checkoutButton;
    }

    public JButton getAddModelButton() {
        return addModelButton;
    }

    // Added getter for model list to set selected index in tests
    public JList<String> getModelList() {
        return modelList;
    }
}
