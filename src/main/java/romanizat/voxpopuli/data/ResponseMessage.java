package romanizat.voxpopuli.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Data
@RequiredArgsConstructor(access = PRIVATE)
public class ResponseMessage<T> {
    private final T message;

    public static <T> ResponseMessage<T> of(T message) {
        return new ResponseMessage<>(message);
    }
}
