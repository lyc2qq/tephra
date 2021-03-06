package org.lpw.tephra.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author lpw
 */
@Component("tephra.util.validator")
public class ValidatorImpl implements Validator {
    private final Map<String, Pattern> patterns = new ConcurrentHashMap<>();

    @Override
    public boolean isEmpty(Object object) {
        if (object == null)
            return true;

        if (object instanceof String)
            return ((String) object).trim().length() == 0;

        if (object.getClass().isArray())
            return Array.getLength(object) == 0;

        if (object instanceof Iterable)
            return !((Iterable<?>) object).iterator().hasNext();

        if (object instanceof Map)
            return ((Map<?, ?>) object).isEmpty();

        return false;
    }

    @Override
    public boolean isEmail(String email) {
        return isMatchRegex("^(?:\\w+\\.?-?)*\\w+@(?:\\w+\\.?-?)*\\w+$", email);
    }

    @Override
    public boolean isMobile(String mobile) {
        return isMatchRegex("^1\\d{10}$", mobile);
    }

    @Override
    public boolean isMatchRegex(String regex, String string) {
        return regex != null && string != null && getPattern(regex).matcher(string).matches();
    }

    private Pattern getPattern(String regex) {
        return patterns.computeIfAbsent(regex, Pattern::compile);
    }

    @Override
    public boolean startsWith(byte[] bytes, byte[] prefix) {
        if (isEmpty(bytes) || isEmpty(prefix) || bytes.length < prefix.length)
            return false;

        for (int i = 0; i < prefix.length; i++)
            if (bytes[i] != prefix[i])
                return false;

        return true;
    }
}
