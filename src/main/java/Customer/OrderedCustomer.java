package Customer;

public class OrderedCustomer {
    private String id;
    private String order;

    public OrderedCustomer( String order){
                this.setOrder(order);

    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
