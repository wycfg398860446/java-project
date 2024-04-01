package service.pay.impl.strategy;

import service.pay.PaymentCallbackStrategy;

/**
 * 定义支付失败策略类
 */
public class PaymentFailedStrategy implements PaymentCallbackStrategy {
    @Override
    public void execute(String orderNo) {
        System.out.println("处理支付失败的订单: " + orderNo);
        // 这里添加处理支付失败的业务逻辑，例如通知用户等
    }
}
