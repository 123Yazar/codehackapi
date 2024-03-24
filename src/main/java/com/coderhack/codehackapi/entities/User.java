package com.coderhack.codehackapi.entities;

import java.util.HashSet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    @NonNull @NotNull
    private String id;
    @NonNull @NotNull
    private String username;
    private int score;
    private HashSet<String> badge;
}
