package ge.com.cm.ctrl;

import ge.com.cm.utils.MethodResponse;
import javax.lang.model.type.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    //private static final Logger LOGGER = LogManager.getLogger(AppConfig.LOGGER_NAME);
    
    
    
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MethodResponse handleInternalServerError(HttpServletRequest request, Exception ex) {
        //LOGGER.error("handleInternalServerError {}", request.getRequestURI(), ex);
        System.out.println("gggggg:  " + ex.getMessage() + "  " + request.getRequestURI());
        return new MethodResponse(ex);
    }
}