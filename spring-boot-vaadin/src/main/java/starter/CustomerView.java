package starter;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Nasir on 05-09-2015.
 */
@SpringView(name = "customer")
public class CustomerView extends VerticalLayout implements View{

    @Autowired
    private CustomerService customerService;
    private Grid grid;

    @PostConstruct
    protected void initialize() {
        setSizeFull();

        grid = new Grid();
        grid.setSizeFull();

        grid.addColumn("firstName", String.class);
        grid.addColumn("lastName", String.class);

        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        grid.setContainerDataSource(new BeanItemContainer<Customer>(
                Customer.class, customerService.getCustomers()));
    }
}
