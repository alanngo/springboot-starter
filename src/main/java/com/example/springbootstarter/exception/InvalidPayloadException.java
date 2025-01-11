package com.example.springbootstarter.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidPayloadException extends Exception
{
    private final Collection<String> messages;
}
