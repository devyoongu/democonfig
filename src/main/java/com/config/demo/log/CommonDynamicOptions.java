package com.config.demo.log;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

/**
 * 대표님이 회의에서 말씀하셨던 다양한 Dynamic 옵션이 있을 수 있다고 생각하고 그것들을 일괄적으로 처리하는 Signleton
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonDynamicOptions {

    private LogLevel logLevel;

    private LogLevel getLogLevelFromString(String propertiesLogLevel) {
        switch (propertiesLogLevel) {
            case "trace":
                return LogLevel.TRACE;
            case "debug":
                return LogLevel.DEBUG;
            case "warn":
                return LogLevel.WARN;
            case "error":
                return LogLevel.ERROR;
            default:
                return LogLevel.INFO;
        }
    }

    public void setLogLevel(String propertiesLogLevelString) {
        this.logLevel = getLogLevelFromString(propertiesLogLevelString);
    }

    public static CommonDynamicOptions getInstance() {
        return Holder.SINGLETON_LAZY_HOLDER;
    }

    private static class Holder {

        private static final CommonDynamicOptions SINGLETON_LAZY_HOLDER = new CommonDynamicOptions();
    }
}
