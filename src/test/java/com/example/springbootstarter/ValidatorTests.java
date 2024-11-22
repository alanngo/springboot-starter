package com.example.springbootstarter;

import com.example.springbootstarter.exception.InvalidPayloadException;
import com.example.springbootstarter.helper.JsonObject;
import com.example.springbootstarter.helper.Pair;
import com.example.springbootstarter.util.Validators;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class ValidatorTests {

    @Test
    void requiredPass() {
        assertDoesNotThrow(() -> {
            JsonObject o = new JsonObject(Pair.create("key", "value"));
            Validators.init(o).required("key").close();
        });
    }

    @Test
    void requiredFail() {
        assertThrows(InvalidPayloadException.class, () -> {
            JsonObject o = new JsonObject(Pair.create("key", null));
            Validators.init(o).required("key").close();
        });
    }

    @Test
    void ltPass() {
        assertDoesNotThrow(() -> {
            JsonObject o = new JsonObject(Pair.create("key", 10));
            Validators.init(o).lt("key", 11.0).close();
        });
    }

    @Test
    void ltFail() {
        assertThrows(InvalidPayloadException.class, () -> {
            JsonObject o = new JsonObject(Pair.create("key", 10));
            Validators.init(o).lt("key", 9.0).close();
        });
    }

    @Test
    void ltePass() {
        assertDoesNotThrow(() -> {
            JsonObject o = new JsonObject(Pair.create("key", 10.0));
            Validators.init(o).lte("key", 10.0).close();
        });
    }

    @Test
    void lteFail() {
        assertThrows(InvalidPayloadException.class, () -> {
            JsonObject o = new JsonObject(Pair.create("key", 10));
            Validators.init(o).lte("key", 9.0).close();
        });
    }

    @Test
    void gtPass() {
        assertDoesNotThrow(() -> {
            JsonObject o = new JsonObject(Pair.create("key", 10));
            Validators.init(o).gt("key", 5.0).close();
        });
    }

    @Test
    void gtFail() {
        assertThrows(InvalidPayloadException.class, () -> {
            JsonObject o = new JsonObject(Pair.create("key", 10));
            Validators.init(o).gt("key", 11.0).close();
        });
    }

    @Test
    void gtePass() {
        assertDoesNotThrow(() -> {
            JsonObject o = new JsonObject(Pair.create("key", 10.0));
            Validators.init(o).gte("key", 10.0).close();
        });
    }

    @Test
    void gteFail() {
        assertThrows(InvalidPayloadException.class, () -> {
            JsonObject o = new JsonObject(Pair.create("key", 10));
            Validators.init(o).gte("key", 11.0).close();
        });
    }

    @Test
    void inRangeInclusivePass() {
        assertDoesNotThrow(() -> {
            JsonObject o = new JsonObject(Pair.create("key", 10));
            Validators.init(o).inRangeInclusive("key", 4.0, 10.0).close();
        });
    }

    @Test
    void inRangeInclusiveFail() {
        assertThrows(InvalidPayloadException.class, () -> {
            JsonObject o = new JsonObject(Pair.create("key", 12));
            Validators.init(o).inRangeInclusive("key", 4.0, 10.0).close();
        });
    }

    @Test
    void inRangeExclusivePass() {
        assertDoesNotThrow(() -> {
            JsonObject o = new JsonObject(Pair.create("key", 6));
            Validators.init(o).inRangeExclusive("key", 4.0, 10.0).close();
        });
    }

    @Test
    void inRangeExclusiveFail() {
        assertThrows(InvalidPayloadException.class, () -> {
            JsonObject o = new JsonObject(Pair.create("key", 12));
            Validators.init(o).inRangeExclusive("key", 4.0, 10.0).close();
        });
    }


}
