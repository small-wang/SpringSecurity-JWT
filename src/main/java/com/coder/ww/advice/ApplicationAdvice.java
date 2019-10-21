package com.coder.ww.advice;

import com.coder.ww.exception.BusinessException;
import com.coder.ww.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationAdvice.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleThrowable(Throwable t) {
        LOGGER.error(t.getMessage(), t);
        return create(t.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleSystemException(SystemException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return create(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleBusinessException(BusinessException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return create(ex.getMessage());
    }

    private Map create(String msg) {
        Map map = new HashMap<String, String>();
        map.put("errorMsg", msg);
        return map;
    }
}
