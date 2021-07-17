package Customer;

public class OrderedCustomer {
    private String id;
    private String order;

    public OrderedCustomer(String id, String order){
        this.setId(id);
        this.setOrder(order);

    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}