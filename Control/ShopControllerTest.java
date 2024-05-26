package Control;

import Control.ShopController;
import Model.Shop;
import View.ShopView;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class ShopControllerTest {

    private Shop model;
    private ShopView view;
    private ShopController controller;

    @Before
    public void setUp() {
        model = new Shop();
        view = new ShopView();
        controller = new ShopController(model, view);

        // Initialize the shop with a test model
        Shop initialModel = new Shop();
        initialModel.setName("Test Model");
        initialModel.setPrice(100.0);
        initialModel.setDescription("A test model.");
        model.addModel(initialModel);

        view.addModel("Test Model, Price: 100.0, Description: A test model.");
    }

    @Test
    public void testAddToCart() {
        // Simulate selecting an item from the shop
        view.getModelList().setSelectedIndex(0); // Select the first item

        // Perform the add to cart action
        view.getAddToCartButton().doClick();

        // Verify that the item is added to the cart
        assertEquals(1, view.getCartItems().size());
        assertTrue(view.getCartItems().get(0).contains("Test Model"));
    }

    @Test
    public void testCheckout() {
        // Simulate adding items to the cart
        view.getModelList().setSelectedIndex(0); // Select the first item
        view.getAddToCartButton().doClick();
        view.getAddToCartButton().doClick(); // Add the same item twice

        // Perform the checkout action
        view.getCheckoutButton().doClick();

        // Verify that the total price is calculated correctly and displayed
        assertEquals("The total price: 200.0", view.getCheckoutMessage());
    }

    @Test
    public void testAddModel() {
        // Simulate user input for adding a new model
        view.setModelNameField("New Model");
        view.setModelPriceField(150.0);
        view.setModelDescriptionField("A new model.");

        // Perform the add model action
        view.getAddModelButton().doClick();

        // Verify that the new model is added to the shop and displayed in the view
        assertEquals(2, model.getModelList().size());
        assertTrue(model.getModelList().get(1).getName().equals("New Model"));
        assertTrue(((DefaultListModel<String>) view.getModelList().getModel()).contains("New Model, Price: 150.0, Description: A new model."));
    }
}
