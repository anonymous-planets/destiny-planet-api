package com.planet.destiny.core.api.module.sender.service;

import com.planet.destiny.core.api.constant.YesNoType;

public interface SenderCallback {
    void execute(YesNoType sendYn);
}
