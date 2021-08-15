package it.bank.account.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

@Singleton
public class LoggerProducer {

    @Produces
    Logger createLogger(InjectionPoint InjectionPoint){
        return LoggerFactory.getLogger(InjectionPoint.getMember().getDeclaringClass().getName());
    }
}
