package util;

import entity.Owner;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class RatingAttributeConverter implements AttributeConverter<Owner.Rating, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Owner.Rating rating) {
        if (rating == null)
            return null;
        return rating.getValue();
    }

    @Override
    public Owner.Rating convertToEntityAttribute(Integer dbData) {
        if (dbData == 100)
            return Owner.Rating.STANDART;
        if (dbData == 200)
            return Owner.Rating.PREMIUM;
        return null;
    }
}
