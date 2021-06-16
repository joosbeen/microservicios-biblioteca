package com.servicio.prestamos.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 *
 * @author benito
 */
@Component
public class FormValid {
    
    private String jsonObject = "";
    private String jsonArray;

    private static final Logger log = LoggerFactory.getLogger(FormValid.class);
    
    public FormValid(){}

    public String errorMessages(BindingResult result) {

        List<Map<String, String>> messages = result.getFieldErrors().stream().map(error -> {
            Map<String, String> _error = new HashMap<>();

            _error.put(error.getField(), error.getDefaultMessage());

            return _error;
        }).collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return jsonString;
    }
    
    public void setJsonObjet(String name, String value){
        if (!jsonObject.equals("")) {
            jsonObject += ",\n";
            jsonObject += "\"" + name + "\":\"" + value + "\"";
        }else {            
            jsonObject += "\"" + name + "\":\"" + value + "\"";
        }
    }
    
    public String getJsonObject(){
        return  "{" + jsonObject + "\n}";
    }
    
    public void resetJsonObject(){
        jsonObject = "";
    }
}
