public class LogLine {
    private String logLine;

    public LogLine(String logLine) {
        this.logLine = logLine;
    }

    public LogLevel getLogLevel() {
        String logs = this.logLine.substring(1,logLine.indexOf(":")-1);
        return switch (logs) {
            case "TRC" -> LogLevel.TRACE;
            case "DBG" -> LogLevel.DEBUG;
            case "INF" -> LogLevel.INFO;
            case "WRN" -> LogLevel.WARNING;
            case "ERR" -> LogLevel.ERROR;
            case "FTL" -> LogLevel.FATAL;
            default -> LogLevel.UNKNOWN;
        };
    }

    public String getOutputForShortLog() {
        LogLevel logLevel = getLogLevel();
        int code = logLevel.getCode();
        String message = this.logLine.substring(this.logLine.indexOf(":")+1).trim();
        return code+":"+message;
    }
}
