package pojo;

import java.math.BigDecimal;

public class OrderDetail {
    private long id;
    private OrderType orderType;
    private long productId;
    private int qty;
    private BigDecimal amount;

    public enum OrderType{
        PURCHASE,
        DISCOUNT
    }

    public long getId() {
        return id;
    }

    public OrderDetail setId(long id) {
        this.id = id;
        return this;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public OrderDetail setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public long getProductId() {
        return productId;
    }

    public OrderDetail setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public int getQty() {
        return qty;
    }

    public OrderDetail setQty(int qty) {
        this.qty = qty;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", productId=" + productId +
                ", qty=" + qty +
                ", amount=" + amount +
                '}';
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderDetail setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
