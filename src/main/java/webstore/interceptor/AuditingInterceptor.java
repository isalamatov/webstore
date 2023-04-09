package webstore.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AuditingInterceptor extends HandlerInterceptorAdapter {
    Logger logger = Logger.getLogger("auditLogger");
    private String user;
    private String productId;
    private String remoteAddress;
    private int remotePort;
    private String remoteHost;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse arg1,
                             Object handler) throws Exception {
        if (request.getRequestURI().endsWith("products/add") &&
                request.getMethod().equals("POST")) {
            user = request.getRemoteUser();
            productId = request.getParameterValues("productId")[0];
        }
        remoteAddress = request.getRemoteAddr();
        remoteHost = request.getRemoteHost();
        remotePort = request.getRemotePort();
        return true;
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception arg3) throws Exception {
        if (request.getRequestURI().endsWith("products/add") &&
                response.getStatus() == 302) {
            logger.info(String.format("A New product[%s] Added by %s on %s", productId, user, getCurrentTime()));
        }
        if (request.getRequestURI().endsWith("webstore/")) {
            String message = String.format("Access to home page from remote address %s, host %s, port %d",
                    remoteAddress,
                    remoteHost,
                    remotePort);
            logger.info(message);
        }
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }
}
