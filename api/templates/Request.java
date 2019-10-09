package com.jdd.merchant.contract.api;

import com.jdd.merchant.contract.api.enums.RequestOriginEnum;
import lombok.Data;

@Data
public class Request<T> {
	private T data;
}
