package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.Item;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<ItemRepo>, QuerydslBinderCustomizer<QItemRepo> {

    @Override
    default void customize(QuerydslBindings bindings, QItemRepo qItemRepo) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    List<Item> findByCity(Long cityId);

    List<Item> findByCategory(Long categoryId);

    List<Item> findByCityAndCategory(Long cityId, Long categoryId);

//    @Override
//    <S extends Item> Page<S> findAll(Example<S> example, Pageable pageable);
}
