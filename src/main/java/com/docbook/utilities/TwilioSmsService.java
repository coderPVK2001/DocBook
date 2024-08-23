package com.docbook.utilities;

import com.docbook.configs.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioSmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(String toPhoneNumber, String messageBody) {
        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(twilioConfig.getFromPhoneNumber()),
                messageBody
        ).create();
    }
}
