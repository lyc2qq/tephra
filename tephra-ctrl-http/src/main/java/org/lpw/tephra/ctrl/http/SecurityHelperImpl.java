package org.lpw.tephra.ctrl.http;

import org.lpw.tephra.util.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * @author lpw
 */
@Controller("tephra.ctrl.http.security-helper")
public class SecurityHelperImpl implements SecurityHelper {
    @Inject
    private Logger logger;
    @Value("${tephra.ctrl.http.security.jsp.enable:false}")
    private boolean jsp;

    @Override
    public boolean isEnable(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (!jsp && isJsp(uri)) {
            logger.warn(null, "疑似JSP请求[{}]，拒绝访问！", uri);

            return false;
        }

        return true;
    }

    private boolean isJsp(String uri) {
        int indexOf = uri.lastIndexOf('.');
        if (indexOf == -1)
            return false;

        String suffix = uri.substring(indexOf).toLowerCase();

        return suffix.contains("jsp");
    }
}
