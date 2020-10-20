package com.taco.loco.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderInformationModel {

    @NonNull
    private long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order")
    @NonNull
    private OrderModel orderDetails;
}
