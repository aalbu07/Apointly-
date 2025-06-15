package org.example.apointly.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

/**********************************************************
 This is the Error form that will be used as a blueprint for all Errors thrown by Controllers
 **********************************************************/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Don't include null fields in the JSON output
public class ApiErrorResponse {

    private int status;
    private String message;
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private Map<String, String> validationErrors;

    public ApiErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
    /**********************************************************
     This constructor is used to create an ApiErrorResponse with validation errors. More detailed error messages can be added here.
     **********************************************************/

    public ApiErrorResponse(int status, String message, String path, Map<String, String> validationErrors) {
        this(status, message, path); //call the constructor with the same arguments
        this.validationErrors = validationErrors;
    }

}
