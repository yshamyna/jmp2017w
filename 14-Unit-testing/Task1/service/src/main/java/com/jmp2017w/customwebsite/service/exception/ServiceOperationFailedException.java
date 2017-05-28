package com.jmp2017w.customwebsite.service.exception;

public class ServiceOperationFailedException extends Exception
{
    public ServiceOperationFailedException()
    {
        super();
    }

    public ServiceOperationFailedException(String message)
    {
        super(message);
    }

    public ServiceOperationFailedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ServiceOperationFailedException(Throwable cause)
    {
        super(cause);
    }
}