package com.relatos_papel.ms_books_catalogue.data.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SearchCriteria<Book> implements Specification<Book> {
    private final List<SearchStatement> list = new LinkedList<SearchStatement>();


    public void add(SearchStatement statement) {
        list.add(statement);
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();
        for (SearchStatement criteria : list) {
            String key = criteria.getKey();
            Object value = criteria.getValue();
            SearchOperation operation = criteria.getOperation();

            if (value instanceof LocalDate) {
                LocalDate dateValue = (LocalDate) value;

                if (operation.equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(root.get(key), dateValue));
                } else if (operation.equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(root.get(key), dateValue));
                } else if (operation.equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(builder.greaterThanOrEqualTo(root.get(key), dateValue));
                } else if (operation.equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(builder.lessThanOrEqualTo(root.get(key), dateValue));
                }
            }
            else {
                if (operation.equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(builder.greaterThanOrEqualTo(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(builder.lessThanOrEqualTo(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.NOT_EQUAL)) {
                    predicates.add(builder.notEqual(root.get(key), value));
                } else if (operation.equals(SearchOperation.EQUAL)) {
                    predicates.add(builder.equal(root.get(key), value));
                } else if (operation.equals(SearchOperation.MATCH)) {
                    predicates.add(builder.like(
                            builder.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%"));
                } else if (operation.equals(SearchOperation.MATCH_END)) {
                    predicates.add(builder.like(
                            builder.lower(root.get(key)), value.toString().toLowerCase() + "%"));
                }
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
