package com.planet.destiny.auth.service.exception.member;

import com.planet.destiny.core.api.constant.ErrorCodeType;
import com.planet.destiny.core.api.exception.BusinessException;

public class AdminMemberException extends BusinessException {

  public AdminMemberException(ErrorCodeType errorCode, String title, String message, String alertMessage) {
    super(errorCode, title, message, alertMessage);
  }
}
