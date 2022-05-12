package hello.core.order.Service;

import hello.core.order.Order;

public interface OrderService {

    Order createOrder (Long memberId, String itemName, int itemPrice);
}
