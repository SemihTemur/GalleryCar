package com.semih.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String ofStatic;

    private MessageType messageType;

    public String prepareMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.name());
        if(this.ofStatic != null) {
            builder.append(" ").append(ofStatic);
        }
        return builder.toString();
    }

}
