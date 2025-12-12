package com.coding.PracticeSpring.PayInterfaceImpl;

import com.coding.PracticeSpring.PayInterface.PaymentServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Qualifier("SMS")
@ConditionalOnProperty(name="notification.type",havingValue="sms")
public class SmsSend implements PaymentServices {
    @Override
    public void send() {
        System.out.println("SMS send");
    }
}
