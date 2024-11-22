package com.example.springbootstarter.util;

import com.example.springbootstarter.exception.InvalidPayloadException;
import com.example.springbootstarter.helper.JsonObject;

import java.util.HashSet;
import java.util.Set;

public class Validators {
    private final Set<String> messages;
    private final JsonObject obj;
    private Validators(JsonObject o) {
        messages = new HashSet<>();
        obj = o;
    }

    private static Double toDouble(Object o) {
        return Double.parseDouble(o.toString());
    }

    private static String comparisonMessage(String key, Double val, Double expected, Comparison criteria) {
        StringBuilder sb = new StringBuilder();
        String actualValue = key+"'s value "+val;
        switch (criteria) {
            case LESS_THAN:
                sb.append(actualValue).append(" must be less than ").append(expected);
                break;
            case GREATER_THAN:
                sb.append(actualValue).append(" must be greater than ").append(expected);
                break;
            case LESS_THAN_EQ:
                sb.append(actualValue).append(" must be less than or equal to ").append(expected);
                break;
            case GREATER_THAN_EQ:
                sb.append(actualValue).append(" must be greater than or equal to ").append(expected);
                break;
            default:
                break;
        }
        return sb.toString();
    }

    /**
     * constructs new validator object (MUST CALL close() to work properly)
     * @return validator object
     */
    public static Validators init(JsonObject o) {
        return new Validators(o);
    }

    private Validators validate(boolean condition, String m) {
        if (!condition) messages.add(m);
        return this;
    }

    private String getMissingKeys(String... keys) {
        Set<String> missingKeys = new HashSet<>();
        for (String k : keys) {
            if (!obj.containsKey(k) || obj.get(k) == null) missingKeys.add("'" + k + "'");
        }
        if (!missingKeys.isEmpty()) return String.join(", ", missingKeys);
        return "";
    }

    /**
     * less than bound
     * @param key value lookup
     * @param bound value must be less than
     * @return Validator object
     */
    public Validators lt(String key, Double bound) {
        Double val = toDouble(obj.get(key));
        return required(key).validate(val < bound,
                comparisonMessage(key, val, bound, Comparison.LESS_THAN));
    }

    /**
     * greater than bound
     * @param key value lookup
     * @param bound value must be greater than
     * @return Validator object
     */
    public Validators gt(String key, Double bound) {
        Double val = toDouble(obj.get(key));
        return required(key).validate(val > bound,
                comparisonMessage(key, val, bound, Comparison.GREATER_THAN));
    }

    /**
     * less than or equals bound
     * @param key value lookup
     * @param bound value must be less than or equal to
     * @return Validator object
     */
    public Validators lte(String key, Double bound) {
        Double val = toDouble(obj.get(key));
        return required(key).validate(val <= bound,
                comparisonMessage(key, val, bound, Comparison.LESS_THAN_EQ));
    }

    /**
     * greater than or equals bound
     * @param key value lookup
     * @param bound value must be greater than or equal to
     * @return Validator object
     */
    public Validators gte(String key, Double bound) {
        Double val = toDouble(obj.get(key));
        return required(key).validate(val >= bound,
                comparisonMessage(key, val, bound, Comparison.GREATER_THAN_EQ));
    }

    /**
     * value is in range including bounds
     * @param key value lookup
     * @param lb lower bound, get(key) >= lb
     * @param ub upper bound get(key) <= ub
     * @return Validator object
     */
    public Validators inRangeInclusive(String key, Double lb, Double ub){
        return gte(key, lb).lte(key, ub);
    }

    /**
     * value is in range excluding bounds
     * @param key value lookup
     * @param lb lower bound, get(key) > lb
     * @param ub upper bound get(key) < ub
     * @return Validator object
     */
    public Validators inRangeExclusive(String key, Double lb, Double ub){
        return gt(key, lb).lt(key, ub);
    }

    /**
     * @param keys required keys that cannot be null
     * @return Validator object
     */
    public Validators required(String... keys) {
        String missingKeys = getMissingKeys(keys);
        return validate(missingKeys.trim().isEmpty(),
                "Payload has missing and/or null-mapped keys: [" + missingKeys + "]");
    }

    /**
     * ends validation process and collects all errors
     * @throws InvalidPayloadException if any violations occur
     */
    public void close() throws InvalidPayloadException {
        if (!messages.isEmpty()) throw new InvalidPayloadException(messages);
    }

    private enum Comparison {
        LESS_THAN, GREATER_THAN, LESS_THAN_EQ, GREATER_THAN_EQ
    }
}
