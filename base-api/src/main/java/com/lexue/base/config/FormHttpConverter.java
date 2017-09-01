/**
 * 
 */
package com.lexue.base.config;

import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * FormHttpMessageConverter
 * <P>
 * 
 */
@Component
public class FormHttpConverter extends FormHttpMessageConverter {
	public FormHttpConverter() {
		List<MediaType> mediaTypeList = new ArrayList<MediaType>();
		mediaTypeList.add(MediaType.APPLICATION_FORM_URLENCODED);
		setSupportedMediaTypes(mediaTypeList);
	}
}
