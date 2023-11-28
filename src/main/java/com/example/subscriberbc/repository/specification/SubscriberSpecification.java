package com.example.subscriberbc.repository.specification;

import com.example.subscriberbc.dto.SearchCriteriaDto;
import com.example.subscriberbc.model.Subscriber;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberSpecification implements Specification<Subscriber> {

    private SearchCriteriaDto criteria;

    @Override
    public Predicate toPredicate(Root<Subscriber> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if(criteria.getValue() == null || criteria.getValue().toString().equals(""))
            return null;
        String key = criteria.getKey();
        Object value = criteria.getValue();
        Path<Boolean> booleanPath = root.get(key);
        if (booleanPath.getJavaType() == Boolean.class && value instanceof Boolean)
            return builder.equal(booleanPath, value);
        if (criteria.getOperation().equalsIgnoreCase("="))
            return builder.equal(builder.upper(root.<String> get(criteria.getKey())), StringUtils.upperCase(criteria.getValue().toString()));
        else if (criteria.getOperation().equalsIgnoreCase("like"))
            return builder.like(builder.upper(root.<String> get(criteria.getKey())), StringUtils.upperCase(criteria.getValue().toString()));
        return null;
    }
}
