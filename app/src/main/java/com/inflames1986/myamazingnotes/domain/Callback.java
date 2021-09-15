package com.inflames1986.myamazingnotes.domain;

public interface Callback<T> {
    void onSuccess(T data);
}
