/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.points;

import java.io.Serializable;
import javax.jms.Queue;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Juneau
 */
@JMSDestinationDefinition(
        name = "java:global/jms/pointsQueue",
        interfaceName = "javax.jms.Queue")
@Named
@SessionScoped
public class SendPointsBean implements Serializable {
    
    @Inject
    JMSContext context;
    
    @Resource(mappedName="java:global/jms/pointsQueue")
    Queue pointsQueue;

    @Pattern(regexp = "^\\d{2},\\d{2}",
            message = "Message format must be 2 digits, comma, 2 digits, e.g. 12,12")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void sendMessage() {
        System.out.println("Sending message: " + message);
        context.createProducer().send(pointsQueue, message);
    }
    
}
