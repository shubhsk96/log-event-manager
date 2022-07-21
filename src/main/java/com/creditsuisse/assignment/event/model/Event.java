package com.creditsuisse.assignment.event.model;

import com.creditsuisse.assignment.log.model.ApplicationLog;
import com.creditsuisse.assignment.log.model.Log;
import com.creditsuisse.assignment.log.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * Represents the Event
 * @author Shubham K
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private String id;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    private long duration;
    private String host;
    private boolean alert;

    public Event(Log log, long duration) {
        this.id = log.getId();
        this.duration = duration;
        this.alert = duration > 4;

        boolean isApplicationLog = log instanceof ApplicationLog;
        this.type = isApplicationLog ? Type.valueOf(((ApplicationLog) log).getType()) : null;
        this.host = isApplicationLog ? String.valueOf(((ApplicationLog) log).getHost()) : null;
    }
}
