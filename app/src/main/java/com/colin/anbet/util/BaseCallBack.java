package com.colin.anbet.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseCallBack<T>
{
  private Type mType = a(getClass());
  
  static Type a(Class<?> paramClass)
  {
    Type genericSuperclass = paramClass.getGenericSuperclass();
    if ((genericSuperclass instanceof ParameterizedType)) {
      return ((ParameterizedType)genericSuperclass).getActualTypeArguments()[0];
    }
    return String.class;
  }
  
  public abstract void onFail(int paramInt, String paramString);
  
  public void onProgress(int paramInt, String paramString) {}
  
  public abstract void onSuccess(T paramT, String paramString);
}



