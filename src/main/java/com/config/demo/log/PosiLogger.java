package com.config.demo.log;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * PosiCube용 Custom Dynamic App Logger CommonDynamicOptions 클래스에 기록된 LogLevel을 바탕으로 로그 출력 Level을 컨트롤하는 기능을 담당.
 */
@Slf4j
@Getter
//@RequiredArgsConstructor
public class PosiLogger {
    public void trace(String msg) {
        if (!isAvailable(LogLevel.TRACE)) {
            return;
        }
        log.trace(getMsg(msg));
    }

    public void trace(String msg, Throwable t) {
        if (!isAvailable(LogLevel.TRACE)) {
            return;
        }
        log.trace(getMsg(msg), t);
    }

    public static void trace(String format, Object... args) {
        if (!isAvailable(LogLevel.TRACE)) {
            return;
        }
        log.trace(getMsg(format), args);
    }

    public static void debug(String msg) {
        if (!isAvailable(LogLevel.DEBUG)) {
            return;
        }
        log.debug(getMsg(msg));
    }

    public static void debug(String msg, Throwable t) {
        if (!isAvailable(LogLevel.DEBUG)) {
            return;
        }
        log.debug(getMsg(msg), t);
    }

    public static void debug(String format, Object... args) {
        if (!isAvailable(LogLevel.DEBUG)) {
            return;
        }
        log.debug(getMsg(format), args);
    }

    public static void info(String msg) {
        if (!isAvailable(LogLevel.INFO)) {
            return;
        }
        log.info(getMsg(msg));
    }

    public static void info(String msg, Throwable t) {
        if (!isAvailable(LogLevel.INFO)) {
            return;
        }
        log.info(getMsg(msg), t);
    }

    public static void info(String format, Object... args) {
        if (!isAvailable(LogLevel.INFO)) {
            return;
        }
        log.info(getMsg(format), args);
    }

    public static void warn(String msg) {
        if (!isAvailable(LogLevel.WARN)) {
            return;
        }
        log.warn(getMsg(msg));
    }

    public static void warn(String msg, Throwable t) {
        if (!isAvailable(LogLevel.WARN)) {
            return;
        }
        log.warn(getMsg(msg), t);
    }

    public static void warn(String format, Object... args) {
        if (!isAvailable(LogLevel.WARN)) {
            return;
        }
        log.warn(getMsg(format), args);
    }

    public static void error(String msg) {
        if (!isAvailable(LogLevel.ERROR)) {
            return;
        }
        log.error(getMsg(msg));
    }

    public static void error(String msg, Throwable t) {
        if (!isAvailable(LogLevel.ERROR)) {
            return;
        }
        log.error(getMsg(msg), t);
    }

    public static void error(String format, Object... args) {
        if (!isAvailable(LogLevel.ERROR)) {
            return;
        }
        log.error(getMsg(format), args);
    }

    /**
     * App(Dialogue Engine or Front or etc...) to Backend where to not use RequestBody
     * TODO : develop get ip logic
     */
    public static void printRequestFromClientLog() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String queryParams = request.getQueryString();

        if (queryParams == null || queryParams.isBlank()) {
            printLogFormatAndParams(LogLevel.INFO,"request [{}] {} from [{}]", request.getMethod(), request.getRequestURI(), getClientIP(request));
        } else {
            printLogFormatAndParams(LogLevel.INFO,"request [{}] {} from [{}] - [Item] : {}", request.getMethod(), request.getRequestURI(), getClientIP(request), queryParams);
        }
    }

    public static void printRequestFromClientLog(LogLevel logLevel) {
        if (!isAvailable(logLevel)) {
            return;
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String format = "request [{}] {} from [{}] - [item] : {}";
        Object[] params = new Object[]{request.getMethod(), request.getRequestURI(), getClientIP(request), request.getQueryString()};

        printLogFormatAndParams(logLevel, format, params);
    }

    public static void printResponseToClientLog(ResponseEntity responseEntity) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String format = "response [{}] {} from [ip] - [item] : {}";
        Object[] params = new Object[]{request.getMethod(), request.getRequestURI(), getClientIP(request), responseEntity.getBody()};

        printLogFormatAndParams(LogLevel.INFO, format, params);
    }

    public static void printResponseToClientLog(LogLevel logLevel, ResponseEntity responseEntity) {
        if (!isAvailable(logLevel)) {
            return;
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String format = "response [{}] {} from [ip] - [item] : {}";
        Object[] params = new Object[]{request.getMethod(), request.getRequestURI(), getClientIP(request), responseEntity.getBody()};

        printLogFormatAndParams(logLevel, format, params);
    }

    public static void printRequestToExternalLog(String methodName, Object... args) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String format = "send [{}] from robi - [ITEM] : {}";

        printLogFormatAndParams(LogLevel.INFO, format, methodName, getClientIP(request), args);
    }

    public static void printResponseFromExternalLog() {

    }

    private static void printLogMsg(LogLevel logLevel, String msg) {
        if (logLevel == LogLevel.TRACE) {
            log.trace(msg);
        } else if (logLevel == LogLevel.DEBUG) {
            log.debug(msg);
        } else if (logLevel == LogLevel.INFO) {
            log.info(msg);
        } else if (logLevel == LogLevel.WARN) {
            log.warn(msg);
        } else if (logLevel == LogLevel.ERROR) {
            log.error(msg);
        } else {
            //TODO : Exception Logic
        }
    }

    private static void printLogMsgAndThrowable(LogLevel logLevel, String msg, Throwable t) {
        if (logLevel == LogLevel.TRACE) {
            log.trace(msg, t);
        } else if (logLevel == LogLevel.DEBUG) {
            log.debug(msg, t);
        } else if (logLevel == LogLevel.INFO) {
            log.info(msg, t);
        } else if (logLevel == LogLevel.WARN) {
            log.warn(msg, t);
        } else if (logLevel == LogLevel.ERROR) {
            log.error(msg, t);
        } else {
            //TODO : Exception Logic
        }
    }

    private static void printLogFormatAndParams(LogLevel logLevel, String format, Object... params) {
        if (logLevel == LogLevel.TRACE) {
            trace(format, params);
        } else if (logLevel == LogLevel.DEBUG) {
            debug(format, params);
        } else if (logLevel == LogLevel.INFO) {
            info(format, params);
        } else if (logLevel == LogLevel.WARN) {
            warn(format, params);
        } else if (logLevel == LogLevel.ERROR) {
            error(format, params);
        } else {
            //TODO : Exception Logic
        }
    }

    private static String getClientIP(HttpServletRequest request) {
        String ip = null;

        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isBlank()) {
            return ip;
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (ip != null && !ip.isBlank()) {
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && !ip.isBlank()) {
            return ip;
        }

        ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip != null && !ip.isBlank()) {
            return ip;
        }

        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip != null && !ip.isBlank()) {
            return ip;
        }

        ip = request.getRemoteAddr();
        if (ip != null && !ip.isBlank()) {
            return ip;
        }

        throw new RuntimeException();
    }

    private static boolean isAvailable(LogLevel logLevel) {
        return logLevel.ordinal() >= CommonDynamicOptionsConfig.commonDynamicOptions.getLogLevel().ordinal();
    }

    private static String getMsg(String msg) {
        String appName = CommonDynamicOptionsConfig.commonDynamicOptions.getAppName();
        return "[" + appName + "]" + msg;
    }
}