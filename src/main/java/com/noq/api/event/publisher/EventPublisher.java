package com.noq.api.event.publisher;

import com.google.gson.Gson;
import com.noq.dependencies.db.model.User;
import com.noq.event.model.OnRegistrationCompleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class EventPublisher implements ApplicationEventPublisherAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

    ApplicationEventPublisher eventPublisher;

    Gson gson = new Gson();

    public void publishEvent(User registeredUser, Locale locale, String appUrl) {
        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(registeredUser, locale, appUrl);
        eventPublisher.publishEvent(event);
        LOGGER.info("Published event:"+gson.toJson(event));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
