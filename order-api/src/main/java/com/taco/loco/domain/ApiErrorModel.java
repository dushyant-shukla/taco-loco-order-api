package com.taco.loco.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ApiErrorModel {

    @NonNull
    private List<String> errors;
}
