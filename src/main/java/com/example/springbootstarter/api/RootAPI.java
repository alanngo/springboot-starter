package com.example.springbootstarter.api;

import com.example.springbootstarter.helper.JsonArray;
import com.example.springbootstarter.helper.JsonObject;
import com.example.springbootstarter.helper.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class RootAPI
{
    @GetMapping("/")
    public ResponseEntity<?> root(){
        JsonObject obj = new JsonObject(
                Pair.create("string", "hello world"),
                Pair.create("number", 3.1415926),
                Pair.create("boolean", true),
                Pair.create("null", null),
                Pair.create("array", new JsonArray("king", "queen", "rook", "bishop", "knight", "pawn")),
                Pair.create("object", new JsonObject(
                        Pair.create("Ace", 14),
                        Pair.create("King", 13),
                        Pair.create("Queen", 12),
                        Pair.create("Jack", 11)
                ))
        );
        return ResponseEntity.ok(obj);
    }
    @PostMapping("/")
    public ResponseEntity<?> cringe(@RequestBody Object body){

        return ResponseEntity.ok(body);
    }
}
