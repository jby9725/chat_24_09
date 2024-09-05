package com.koreait.exam.chat_24_09;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;
}
