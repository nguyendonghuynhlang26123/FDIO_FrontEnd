package com.team3.fdiosystem.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class Event<T> {
    MutableLiveData<T> handler = new MutableLiveData<T>();

    public void trigger(T value){
        handler.setValue(value);
    }

    public T getValue(){
        return handler.getValue();
    }

    public void bindEvent(@NonNull final LifecycleOwner owner, @NonNull final Observer<T> observer) {
        // Observe the internal MutableLiveData
        handler.observe(owner, t -> {
            observer.onChanged(t);
        });
    }
}
