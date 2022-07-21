package com.creditsuisse.assignment.log.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationLog extends Log {
    private String type;
    private String host;

    public ApplicationLog(String id, String state, long timestamp, String type, String host) {
        super(id, state, timestamp);
        this.type = type;
        this.host = host;
    }
}
