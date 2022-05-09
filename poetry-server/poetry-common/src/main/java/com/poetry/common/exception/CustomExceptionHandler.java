package com.poetry.common.exception;

import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice(value = {"com.poetry.server.admin", "com.poetry.server.miniprogram"})
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handler(Exception e){
        if(e instanceof CustomException){
            CustomException exception = (CustomException) e;
            log.error("错误警告：" + exception.getErrMsg(),exception);
            return new ErrorResponse(exception.getCode(),exception.getErrMsg(),e.getMessage());
        }else if(e instanceof MethodArgumentNotValidException){
            BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
            StringBuilder stringBuilder = new StringBuilder();
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();
                if (errors != null) {
                    errors.forEach(p -> {
                        FieldError fieldError = (FieldError) p;
                        log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]",fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                        stringBuilder.append(fieldError.getDefaultMessage());
                    });
                }
            }
            return new ErrorResponse(ErrorCode.INVALID_PARAM.getCode(), stringBuilder.toString(),e.getMessage());
        } else {
            log.error("系统异常：",e);
            return new ErrorResponse(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getSemantics(),e.getMessage());
        }
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ErrorResponse handler(NullPointerException e){
        log.error("空指针异常：",e);
        return new ErrorResponse(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getSemantics(),e.getMessage());
    }
}
