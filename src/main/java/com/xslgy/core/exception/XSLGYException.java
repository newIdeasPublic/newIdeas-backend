package com.xslgy.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class XSLGYException
        extends
        RuntimeException
{
    private String message;
    private int    code = 500;

    public XSLGYException(String message)
    {
        super(message);
        this.message = message;
    }

    public XSLGYException(String message,
                          Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    public XSLGYException(String message,
                          int code)
    {
        super(message);
        this.message = message;
        this.code = code;
    }

}
