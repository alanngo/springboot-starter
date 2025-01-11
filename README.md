# SpringBoot Starter

## Helper Classes

### JsonObject

```java
import com.example.springbootstarter.helper.JsonArray;
import com.example.springbootstarter.helper.JsonObject;
import com.example.springbootstarter.helper.Pair;

JsonObject blank = JsonObject.create(); // create a blank json object

JsonObject singleton = JsonObject.create("key", "value"); // create a single-entry object

JsonObject obj = JsonObject.create( // creating sample object
        Pair.create("string", "lorem ipsum"),
        Pair.create("number", 3.14),
        Pair.create("boolean", true),
        Pair.create("object", JsonObject.create("key", "value")),
        Pair.create("array", JsonArray.create("ace", "king", "queen", "jack", "10"))
);
```

### JsonArray

```java
import com.example.springbootstarter.helper.JsonArray;
import com.example.springbootstarter.helper.JsonObject;
import com.example.springbootstarter.helper.Pair;

JsonArray blank = JsonArray.create(); // create a blank json array

JsonArray obj = JsonArray.create(
        "SpringBoot", 
        true, 
        42,
        JsonArray.create("ace", "king", "queen", "jack", "10"),
        JsonObject.create("key", "value"),
        null
); // create a sample array with multiple typed values
```