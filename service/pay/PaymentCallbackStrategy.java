package service.pay;

/**
 * 定义支付回调策略接口
 */
public interface PaymentCallbackStrategy {
    void execute(String orderNo);
}
