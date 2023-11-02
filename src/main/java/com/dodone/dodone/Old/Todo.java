package com.dodone.dodone.Old;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


// Git key as a password for pushing
// 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


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private int id;
    private String name;
    private int rating;
    private boolean priority;
    private boolean done;

}
