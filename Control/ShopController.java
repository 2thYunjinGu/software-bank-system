package Control;

import Model.Shop;
import View.ShopView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopController {
    private Shop model;
    private ShopView view;

    public ShopController(Shop model, ShopView view) {
        this.model = model;
        this.view = view;

        initView();
        initListeners();
    }

    private void initView() {
        for (Shop model : model.getModelList()) {
            view.addModel(model.toString());
        }
    }

    private void initListeners() {
        view.setAddToCartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = view.getModelListSelectedIndex();
                if (selectedIndex != -1) {
                    Shop selectedModel = model.getModelList().get(selectedIndex);
                    view.addToCart(selectedModel.toString());
                }
            }
        });

        view.setCheckoutButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalPrice = 0;
                for (String item : view.getCartItems()) {
                    totalPrice += Double.parseDouble(item.split("Price: ")[1].split(",")[0]);
                }
                view.showCheckoutMessage("The total price: " + totalPrice);
            }
        });

        view.setAddModelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getModelName();
                double price = view.getModelPrice();
                String description = view.getModelDescription();

                Shop newModel = new Shop();
                newModel.setName(name);
                newModel.setPrice(price);
                newModel.setDescription(description);
                model.addModel(newModel);

                view.addModel(name + ", Price: " + price + ", Description: " + description);
            }
        });
    }
}











