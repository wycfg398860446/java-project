package service.pay.impl.strategy;


import service.pay.PaymentCallbackStrategy;

/**
 * 定义重复支付策略类
 */
public class DuplicatePaymentStrategy implements PaymentCallbackStrategy {
    @Override
    public void execute(String orderNo) {
        System.out.println("处理重复支付的订单: " + orderNo);
        // 这里添加处理重复支付的业务逻辑，例如退款等
    }
}
