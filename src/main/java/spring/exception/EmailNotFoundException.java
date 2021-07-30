package spring.exception;

import org.aspectj.apache.bcel.classfile.annotation.RuntimeTypeAnnos;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String message) {
        super(message);
    }
}
