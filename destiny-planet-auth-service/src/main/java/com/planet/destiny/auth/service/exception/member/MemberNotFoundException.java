package com.planet.destiny.auth.service.exception.member;

import com.planet.destiny.auth.service.constant.ErrorCodeAuth;
import com.planet.destiny.core.api.constant.ErrorCodeType;
import com.planet.destiny.core.api.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException() {
        super(ErrorCodeAuth.MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(ErrorCodeType errorCode) {
        super(errorCode);
    }

    public MemberNotFoundException(ErrorCodeType errorCode, String title, String message) {
        super(errorCode, title, message);
    }
}
