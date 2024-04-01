package service.pay.impl.context;

import service.pay.PaymentCallbackStrategy;
import service.pay.impl.strategy.DuplicatePaymentStrategy;
import service.pay.impl.strategy.PaymentFailedStrategy;
import service.pay.impl.strategy.PaymentSuccessStrategy;
import service.pay.result.PaymentResult;

/**
 * 定义上下文类,用于调用选择策略
 */
public class PaymentCallbackContext {
    private PaymentCallbackStrategy strategy;

    public PaymentCallbackContext(PaymentResult result) {
        switch (result.getStatus()) {
            case SUCCESS:
                this.strategy = new PaymentSuccessStrategy();
                break;
            case FAILURE:
                this.strategy = new PaymentFailedStrategy();
                break;
            case DUPLICATE:
                this.strategy = new DuplicatePaymentStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown payment status");
        }
    }

    public void handle(String orderNo) {
        if (strategy != null) {
            strategy.execute(orderNo);
        }
    }
}
