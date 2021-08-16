package starter;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Nasir on 05-09-2015.
 */
@SpringUI
@Theme("valo")
public class CustomerUI extends UI {

    private Navigator navigator;

    @Autowired
    private SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        MenuBar menuBar = new MenuBar();
        Panel viewContent = new Panel();

        layout.addComponents(menuBar, viewContent);
        layout.setSizeFull();
        viewContent.setSizeFull();
        layout.setExpandRatio(viewContent, 1);

        navigator = new Navigator(this, viewContent);
        navigator.addProvider(springViewProvider);

        menuBar.addItem("Dashboard", e -> onDashBoardClick());
        menuBar.addItem("Customer", e -> onCustomerClick());

        setContent(layout);

        navigator.navigateTo("dashboard");
    }

    private void onDashBoardClick() {
        navigator.navigateTo("dashboard");
    }

    private void onCustomerClick() {
        navigator.navigateTo("customer");
    }
}
