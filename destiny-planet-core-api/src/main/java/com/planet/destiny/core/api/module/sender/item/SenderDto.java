package com.planet.destiny.core.api.module.sender.item;

import com.planet.destiny.core.api.constant.SenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SenderDto implements Serializable {
    private SenderType senderType;

    public SenderDto(SenderType senderType) {
        this.senderType = senderType;
    }
}
