package com.netcracker.hotelbe.service.validation;

import com.netcracker.hotelbe.entity.ApartmentPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import java.sql.Timestamp;
import java.util.Set;

public class ApartmentPriceValidator implements Validator {

    @Autowired
    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> aClass) {
        return ApartmentPrice.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Set<ConstraintViolation<Object>> validates = validator.validate(o);

        validates.forEach(
                cv -> errors.rejectValue(
                        cv.getPropertyPath().toString(),
                        "",
                        cv.getMessage())
        );

        ApartmentPrice apartmentPrice = (ApartmentPrice) o;

        Timestamp currentTime = new Timestamp(System.currentTimeMillis() - 120000);

        if(apartmentPrice.getStartPeriod().compareTo(currentTime) < 0){
            errors.rejectValue("startPeriod","", "Start period cant be before current date ");
        }
        if(apartmentPrice.getEndPeriod().compareTo(currentTime) < 0){
            errors.rejectValue("end","", "End period cant be before current date ");
        }
        if(apartmentPrice.getEndPeriod().compareTo(apartmentPrice.getStartPeriod()) < 0){
            errors.rejectValue("end","", "End period cant be before Start period");
        }




//        if(task.getComplete() != null )
//            if (task.getComplete().compareTo(task.getAccept()) < 0){
//                errors.rejectValue("complete","", "Complete date cant be before Start date");
//            }
//
//        if(task.getExecutor() != null)
//            if(task.getExecutor().isActive()){
//                errors.rejectValue("executor","", "Executor should be active");
//            }
//
//        if(task.getCreator().isActive()){
//            errors.rejectValue("creator","", "Creator should be active");
//        }
    }
}
