package com.mpt.MadhyaPradeshTourism.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class MPTApplicationExceptions extends RuntimeException  {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
    private final List<String> errors;
    private final transient Object data;
    private final Integer cellulantStatusCode;


    public MPTApplicationExceptions(String message) {
        this(HttpStatus.BAD_REQUEST, message);
    }

    public MPTApplicationExceptions(HttpStatus httpStatus, String message) {
        this(httpStatus, message, Collections.singletonList(message), null, null);
    }

    public MPTApplicationExceptions(HttpStatus httpStatus, String message, List<String> errors) {
        this(httpStatus, message, errors, null, null);
    }


    public MPTApplicationExceptions(HttpStatus httpStatus, String message, List<String> errors, Integer cellulantStatusCode, Object data) {
        super(message);
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.data = data;
        this.cellulantStatusCode = cellulantStatusCode;
    }

}