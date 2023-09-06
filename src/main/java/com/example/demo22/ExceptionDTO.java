package com.example.demo22;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@ToString
@Getter
@Builder
public class ExceptionDTO {
    String message;
    UUID uuid;
    ZonedDateTime exceptionServerTime;
    String path;
}