package ru.kerporation.datageneratorgrpcmicroservice.web.mapper;

import java.util.List;

public interface Mappable<E, D> {

    E toEntity(final D d);

    List<E> toEntity(final List<D> d);

    D toDto(final E e);

    List<D> toDto(final List<E> e);
}