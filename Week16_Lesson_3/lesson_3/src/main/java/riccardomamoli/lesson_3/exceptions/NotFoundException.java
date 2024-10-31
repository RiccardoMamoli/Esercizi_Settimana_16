package riccardomamoli.lesson_3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID userId) {
        super(String.valueOf(userId));
    }
}
