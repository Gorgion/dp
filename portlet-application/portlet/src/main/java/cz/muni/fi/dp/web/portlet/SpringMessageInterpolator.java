package cz.muni.fi.dp.web.portlet;

import java.util.Locale;
import javax.validation.MessageInterpolator;
import javax.validation.MessageInterpolator.Context;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * Created by Tomas on 16.04.2016.
 */
public class SpringMessageInterpolator implements MessageInterpolator, MessageSourceAware, InitializingBean {
    private MessageSource messageSource;

    public SpringMessageInterpolator() {
    }

    public String interpolate(String messageTemplate, Context context) {
        return this.messageSource.getMessage(messageTemplate, new Object[0], Locale.getDefault());
    }

    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return this.messageSource.getMessage(messageTemplate, new Object[0], locale);
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void afterPropertiesSet() throws Exception {
        if(this.messageSource == null) {
            throw new IllegalStateException("MessageSource was not injected, could not initialize " + this.getClass().getSimpleName());
        }
    }
}
