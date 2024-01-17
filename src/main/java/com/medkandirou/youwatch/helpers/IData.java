package com.medkandirou.youwatch.helpers;

import java.util.List;

public interface IData<Treq,Tres, ID> {
    Tres findById(ID id);
    List<Tres> findAll();
    Tres save(Treq entity);
    Tres update(Treq entity);
    Treq deleteById(ID id);
}