package com.taco.loco.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ResponseModel {

    @NonNull private int id;
    @NonNull private String message;
}
