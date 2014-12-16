/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.booking;

import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.flow.FlowScoped;
import org.glassfish.movieplex7.rest.MovieFacadeREST;
import org.glassfish.movieplex7.rest.ShowTimingFacadeREST;

/**
 *
 * @author Juneau
 */
@Named(value = "booking")
@FlowScoped("booking")
public class Booking implements java.io.Serializable {

    private int movieId;
    private String startTime;
    private int startTimeId;

    @EJB
    private MovieFacadeREST movieFacade;
    
    @EJB
    private ShowTimingFacadeREST showTimingFacade;

    /**
     * Creates a new instance of Booking
     */
    public Booking() {
    }

    /**
     * @return the movieId
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * @param movieId the movieId to set
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieFacade.getMovieName(movieId);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        StringTokenizer tokens = new StringTokenizer(startTime, ",");
        startTimeId = Integer.parseInt(tokens.nextToken());
        this.startTime = tokens.nextToken();
    }

    public int getStartTimeId() {
        return startTimeId;
    }
    
    public String getTheater(){
        return showTimingFacade.getTheater(movieId, startTimeId);
    }

}
