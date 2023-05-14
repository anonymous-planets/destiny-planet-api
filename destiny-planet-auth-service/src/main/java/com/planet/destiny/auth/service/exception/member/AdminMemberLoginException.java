package com.planet.destiny.auth.service.exception.member;

import com.planet.destiny.auth.service.constant.ErrorCodeAuth;

public class AdminMemberLoginException extends AdminMemberException {

  public AdminMemberLoginException(ErrorCodeAuth errorCode, String title, String message, String alertMessage) {
    super(errorCode, title, message, alertMessage);
  }
  public AdminMemberLoginException(ErrorCodeAuth errorCode, String title, String message) {
    this(errorCode, title, message, errorCode.getAlertMessage());
  }

  public AdminMemberLoginException(ErrorCodeAuth errorCode, String title) {
    this(errorCode, title, errorCode.getMessage());
  }

  public AdminMemberLoginException(ErrorCodeAuth errorCode) {
    this(errorCode, errorCode.getTitle());
  }

  public AdminMemberLoginException() {
    this(ErrorCodeAuth.ADMIN_MEMBER_ERROR);
  }
}
