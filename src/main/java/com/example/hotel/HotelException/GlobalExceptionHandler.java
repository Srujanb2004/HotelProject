package com.example.hotel.HotelException;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hotel.Dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleEnumErrors(HttpMessageNotReadableException ex) {

		String message = ex.getMessage();
		String finalMessage = "Invalid enum value.";

		if (message != null && message.contains("Cannot deserialize value of type")) {
	        if (message.contains("\"\"")) {
	            finalMessage = "Enum value cannot be blank. Use one of the valid values in CAPITAL letters.";
	        }
	    }
		
		if (message != null && message.contains("not one of the values accepted for Enum class")) {
			try {
				int start = message.indexOf("[") + 1;
				int end = message.indexOf("]");
				String allowedValues = message.substring(start, end);

				finalMessage = "Invalid enum value. Use CAPITAL letters. Allowed values: " + allowedValues;
			} catch (Exception e) {
				finalMessage = "Invalid enum value. Enum format incorrect.";
			}
		}

		ApiResponse api = new ApiResponse();
		api.setStatus("failed");
		api.setTimestamp(LocalDateTime.now());
		api.setMessage(finalMessage);
		api.setData(null);

		return ResponseEntity.badRequest().body(api);
	}
	
//	@ExceptionHandler(HotelExceptions.class)
//	public ResponseEntity<?> handleHotelExceptions(HotelExceptions ex) {
//
//	    ApiResponse api = new ApiResponse();
//	    api.setStatus("failed");
//	    api.setMessage(ex.getMessage());
//	    api.setData(null);
//	    api.setTimestamp(LocalDateTime.now());
//
//	    return ResponseEntity.badRequest().body(api);
//	}
}
