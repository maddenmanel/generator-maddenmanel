import ch.qos.logback.classic.filter.ThresholdFilter

import java.nio.charset.Charset

statusListener(OnConsoleStatusListener)

def PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%-25thread] [%X{ip},%X{traceId},%X{appName}] %-5level %logger{36}.%M\\(%line\\) - %msg%n"

def PATH = "/export//Logs/<%= systemName %>"

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = PATTERN
        charset = Charset.forName("utf-8")
    }
}

appender("FILE-ALL", RollingFileAppender) {
    file = "${PATH}/file-all.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = PATTERN
        charset = Charset.forName("utf-8")
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${PATH}/file-all.log.%d"
        maxHistory = 6
    }
}


appender("FILE-ERROR", RollingFileAppender) {
    file = "${PATH}/file-error.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = PATTERN
        charset = Charset.forName("utf-8")
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${PATH}/file-error.log.%d"
        maxHistory = 6
    }
    filter(ThresholdFilter) {
        level = WARN
    }
}


root(INFO, ["CONSOLE", "FILE-ALL", "FILE-ERROR"])
