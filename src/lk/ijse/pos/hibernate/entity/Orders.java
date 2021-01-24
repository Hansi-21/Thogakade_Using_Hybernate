package lk.ijse.pos.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Orders implements SuperEntity{
    @Id
    private String oId;
    private String data;
    @ManyToOne
    private Customer customer;

    public Orders() {
    }

    public Orders(String oId, String data, Customer customer) {
        this.oId = oId;
        this.data = data;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oId='" + oId + '\'' +
                ", data='" + data + '\'' +
                ", customer=" + customer +
                '}';
    }
}
