package service.pay.result;

import service.pay.enums.PaymentStatus;

/**
 * 定义支付结果类
 */
public class PaymentResult {
    private PaymentStatus status;

    public PaymentResult(PaymentStatus status) {
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
