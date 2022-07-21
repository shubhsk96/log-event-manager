package com.creditsuisse.assignment.log.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the Log
 * @author Shubham K
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private String id;
    private String state;
    private long timestamp;
}
