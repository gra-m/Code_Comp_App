package fun.madeby.code_comp_app.services.reporting.exception;

public class ReportingServiceException extends RuntimeException {

public ReportingServiceException() {
    super();
}

public  ReportingServiceException(String message) {
    super(message);
}

public ReportingServiceException(String message, Throwable cause) {
    super(message, cause);
}

}