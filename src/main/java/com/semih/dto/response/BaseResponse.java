package com.semih.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Data
public abstract class BaseResponse {

    private Long id;

    private Date createTime;
}
