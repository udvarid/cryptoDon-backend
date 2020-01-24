package com.donat.crypto.controller;

import lombok.Data;

import java.util.Map;

/**
 * This class is to handle the GraphQl query requests sent by Angular-Apollo
 */
@Data
public class QueryData {
    private String query;
    private Map<String, Object> variables;
}
