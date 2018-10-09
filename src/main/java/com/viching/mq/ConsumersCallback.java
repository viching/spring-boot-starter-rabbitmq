package com.viching.mq;

public interface ConsumersCallback {
    
    void operateMessage(Object message);
}
