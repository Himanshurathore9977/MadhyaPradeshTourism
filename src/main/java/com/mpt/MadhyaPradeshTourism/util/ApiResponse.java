package com.mpt.MadhyaPradeshTourism.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean status ;
    private T data ;
    private String message ;
    private String statsCode;
}
