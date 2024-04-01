import service.pay.enums.PaymentStatus;
import service.pay.impl.context.PaymentCallbackContext;
import service.pay.result.PaymentResult;

public class PaymentCallbackDemo {
    public static void main(String[] args) {
        // 假设这是从支付平台接收到的支付结果
        PaymentResult successResult = new PaymentResult(PaymentStatus.SUCCESS);
        PaymentResult failResult = new PaymentResult(PaymentStatus.FAILURE);
        PaymentResult duplicateResult = new PaymentResult(PaymentStatus.DUPLICATE);

        // 创建上下文并处理回调
        PaymentCallbackContext successContext = new PaymentCallbackContext(successResult);
        PaymentCallbackContext failContext = new PaymentCallbackContext(failResult);
        PaymentCallbackContext duplicateContext = new PaymentCallbackContext(duplicateResult);

        // 模拟订单号
        successContext.handle("123456");
        failContext.handle("789012");
        duplicateContext.handle("345678");
    }
}