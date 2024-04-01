package service.pay.impl.strategy;

import service.pay.PaymentCallbackStrategy;

/**
 * 定义支付成功策略类
 */
public class PaymentSuccessStrategy implements PaymentCallbackStrategy {
    @Override
    public void execute(String orderNo) {
        System.out.println("处理支付成功的订单: " + orderNo);
        // 这里添加处理支付成功的业务逻辑，例如更新订单状态等
    }
}
