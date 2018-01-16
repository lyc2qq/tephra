package org.lpw.tephra.ctrl.http.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lpw
 */
public interface UploadHelper {
    /**
     * 前缀。
     */
    String PREFIX = "tephra.ctrl.http.upload.";
    /**
     * 上传Servlet URI地址。
     */
    String URI = "/tephra/ctrl-http/upload";

    /**
     * 上传文件。
     *
     * @param request  请求HttpServletRequest信息。
     * @param response 输出HttpServletResponse信息。
     */
    void upload(HttpServletRequest request, HttpServletResponse response);
}
