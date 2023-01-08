package com.planet.destiny.core.api.module.sender.service;


import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.module.sender.item.SenderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SenderService <T extends SenderDto> {

    private final SenderType.SenderFactory senderFactory;

    public void send(T sender, SenderCallback callback) {
        senderFactory.getSender(sender.getSenderType()).send(sender, callback);
    }
}
