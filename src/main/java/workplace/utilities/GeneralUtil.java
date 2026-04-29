package workplace.utilities;

import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;


public class GeneralUtil {

    
    public static String getGetterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String getSetterName(String fieldName) {
        return "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }


    public static void checkRequiredProperties(Object objectToCheck, List<String> requiredProperties) throws Exception {

        List<String> errorKeywords = new ArrayList<>();
        Iterator<String> it = requiredProperties.iterator();

        while(it.hasNext()){
            String property = (String) it.next();
            boolean requiredPropertyIsPresent = true;
            Object value = null;

            try {
                value = objectToCheck
                        .getClass()
                        .getMethod(getGetterName(property))
                        .invoke(objectToCheck);
                    
                System.out.println(value);
            }catch(Exception e){
                requiredPropertyIsPresent = false;
            }
            if(value == null){
                requiredPropertyIsPresent = false;
            }
            if(!requiredPropertyIsPresent){
                errorKeywords.add(property+"_REQUIRED");
            }
        }

        if(!errorKeywords.isEmpty()){
            throw new BadRequestException(errorKeywords.stream().collect(Collectors.joining(" ;")));
        }

        
    }

    public static List<Field> getFieldsUpTo(Class<?> startClass, Class<?> exclusiveParent){

        List<Field> currentClassFields = new ArrayList<Field>();
        currentClassFields.addAll(Arrays.asList(startClass.getDeclaredFields()));

        Class<?> parentClass = startClass = startClass.getSuperclass();

        if(parentClass != null && (exclusiveParent == null || !parentClass.equals(exclusiveParent))){
            List<Field> parentClassFields = getFieldsUpTo(parentClass, exclusiveParent);
            currentClassFields.addAll(parentClassFields);
        }

        return currentClassFields;

    }

    
    private static <T1, T2> void copyValue(T1 sourceObject, T2 destinationObject, String propertyName) throws Exception {
        Object sourceValue = sourceObject.getClass().getMethod(getGetterName(propertyName)).invoke(sourceObject);
        Class sourceReturnType = sourceObject.getClass().getMethod(getGetterName(propertyName)).getReturnType();
        Class destinationReturnType = destinationObject.getClass().getMethod(getGetterName(propertyName)).getReturnType();

        Class returnType = null;

        if(sourceReturnType.equals(destinationReturnType)){
            if(sourceValue != null){
                destinationObject.getClass().getMethod(getSetterName(propertyName), sourceReturnType)
                .invoke(destinationObject, sourceValue);
            }
        }
    }
     

    public static <T1, T2> T2 getCopyOf(T1 source, T2 destination, String... excludeProps) throws Exception {

        List<Field> destinationFields = getFieldsUpTo(destination.getClass(), Object.class);
        List<String> destinationFieldNames = new ArrayList<String>();
        Iterator destinationFieldsIt = destinationFields.iterator();


        while(destinationFieldsIt.hasNext()){
            Field item = (Field) destinationFieldsIt.next();
            destinationFieldNames.add(item.getName());
        }

        List<String> excludedProperties = Arrays.asList(excludeProps);
        Iterator sourceFieldsIt = getFieldsUpTo(source.getClass(), Object.class).iterator();

        while(sourceFieldsIt.hasNext()){
            Field sourceField = (Field) sourceFieldsIt.next();
            if (!Modifier.isStatic(sourceField.getModifiers()) && excludedProperties.indexOf(sourceField.getName()) <= -1
                    && destinationFieldNames.indexOf(sourceField.getName()) != -1) {
                copyValue(source, destination, sourceField.getName());
            }
        }

        return destination;

    }

    /*
    method that checks for non primitive data */
    
}
