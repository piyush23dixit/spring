package com.coding.PracticeSpring.PayInterfaceImpl;

import com.coding.PracticeSpring.PayInterface.PaymentServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Qualifier("Email")
@ConditionalOnProperty(name="notification.type",havingValue="email")
public class EmailSend implements PaymentServices {
    @Override
    public void send() {
        System.out.println("Email send");
    }
}
