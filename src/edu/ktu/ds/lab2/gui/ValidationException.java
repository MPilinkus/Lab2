/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab2.gui;

/**
 * Nuosava situacija, panaudota dialogo struktūrose įvedamų parametrų
 * tikrinimui.
 */
public class ValidationException extends RuntimeException {

    // Situacijos kodas. Pagal ji programuojama programos reakcija į situaciją
    private int code;

    public ValidationException(String text) {
        // (-1) - susitariama, kad tai neutralus kodas.
        this(text, -1);
    }

    public ValidationException(String message, int code) {
        super(message);
        if (code < -1) {
            throw new IllegalArgumentException("Illegal code in Validation Exception: " + code);
        }
        this.code = code;
    }

    public ValidationException(String message, Throwable throwable, int code) {
        super(message, throwable);
        if (code < -1) {
            throw new IllegalArgumentException("Illegal code in MyException: " + code);
        }
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
