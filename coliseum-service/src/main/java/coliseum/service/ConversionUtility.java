package coliseum.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;

//@Component
public class ConversionUtility implements ApplicationContextAware {

//    @Autowired
    ApplicationContext applicationContext;

    ConversionService conversionService;

    public Object convert(Object source, Class sourceType, Class targetType) {
        return conversionService.convert(source, TypeDescriptor.valueOf(sourceType),
                TypeDescriptor.valueOf(targetType));
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        DefaultConversionService defaultConversionService = applicationContext.getBean(DefaultConversionService.class);
        this.conversionService = defaultConversionService;

        Map<String, GenericConverter> genericConverters = applicationContext.getBeansOfType(GenericConverter.class);

        for (Entry<String, GenericConverter> entry : genericConverters.entrySet()) {
            defaultConversionService.addConverter(entry.getValue());
        }
    }
}