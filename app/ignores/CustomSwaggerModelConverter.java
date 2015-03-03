package ignores;


import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.converter.SwaggerSchemaConverter;
import com.wordnik.swagger.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Option;
import scala.collection.immutable.Map;

import javax.xml.bind.annotation.XmlElement;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class CustomSwaggerModelConverter extends SwaggerSchemaConverter {

    private static final Logger log = LoggerFactory.getLogger(CustomSwaggerModelConverter.class);

    @Override
    public Option<Model> read(Class<?> modelClass, Map<String, String> typeMap) {
        Option<Model> modelOption = super.read(modelClass, typeMap);

        Class<?> currentClass = modelClass;

        while (currentClass.getSuperclass() != null) {
            for (Method method : modelClass.getDeclaredMethods()) {
                processApiModelPropertyMethods(currentClass, method, modelOption);
            }
            currentClass = currentClass.getSuperclass();
        }
        return modelOption;
    }

    private void processApiModelPropertyMethods(Class<?> currentClass, Method method, Option<Model> modelOption) {
        try {
            if (method.isAnnotationPresent(ApiModelProperty.class)) {
                hideFieldsWithHiddenAccess(currentClass, method, modelOption);
            }
        } catch (SecurityException e) {
            log.error("Failed to process method", e);
        }
    }

    private void hideFieldsWithHiddenAccess(Class<?> currentClass, Method method, Option<Model> modelOption) {
        if (!method.getAnnotation(ApiModelProperty.class).access().isEmpty() && method.getAnnotation(ApiModelProperty.class).access().equals("hidden")) {
            String fieldName;
            if (method.isAnnotationPresent(XmlElement.class) && !method.getAnnotation(XmlElement.class).name().isEmpty()) {
                fieldName = method.getAnnotation(XmlElement.class).name();
            } else {
                fieldName = getFieldNameFromMethod(currentClass, method);
            }
            if (fieldName != null) {
                modelOption.get().properties().remove(fieldName);
            }
        }
    }

    private String getFieldNameFromMethod(Class<?> currentClass, Method method) {
        try {
            for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(currentClass).getPropertyDescriptors()) {
                if (propertyDescriptor.getReadMethod().getName().equals(method.getName())) {
                    return propertyDescriptor.getName();
                }
            }
        } catch (IntrospectionException e) {
            log.error("Failed to get field name", e);
        }
        return null;
    }
    
}
