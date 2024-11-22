package com.example.springbootstarter.exception;

import java.util.Collection;

public class InvalidPayloadException extends Exception {

    public InvalidPayloadException(Collection<String> messageList){
        messages = messageList;
        messageList.clear();
    }
    public Collection<String> getMessages()
    {
        if (!messages.isEmpty())
            return messages;
        return null;
    }
    private final Collection<String> messages;
}
