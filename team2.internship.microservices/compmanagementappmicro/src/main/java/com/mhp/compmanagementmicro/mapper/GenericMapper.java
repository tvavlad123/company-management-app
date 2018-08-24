package com.mhp.compmanagementmicro.mapper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class GenericMapper<M, E extends Object> {
   
   public abstract M toInternal(E epo);
   
   public abstract E toExternal(M model);
   
   public List<M> toInternals(Collection<E> epos) {
      List<M> list = new LinkedList<>();
      for (E e : epos) {
         list.add(toInternal(e));
      }
      return list;
   }
   
   public List<E> toExternals(Collection<M> models) {
      List<E> list = new LinkedList<>();
      for (M m : models) {
         list.add(toExternal(m));
      }
      return list;
   }
}
