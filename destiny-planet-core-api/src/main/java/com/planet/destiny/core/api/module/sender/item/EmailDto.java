package com.planet.destiny.core.api.module.sender.item;

import com.planet.destiny.core.api.constant.SenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailDto extends SenderDto implements Serializable {

    private Long idx;
    private List<PersonInfo> toInfos = new ArrayList<>();
    private PersonInfo fromInfo;
    private String subject;
    private String content;
    private Boolean isUseHtml = true;
    private String templateFileName;
    private Map<String, Object> params = new HashMap<>();
    private List<AttachFile> attachFiles = new ArrayList<>();

    @Builder
    public EmailDto(Long idx, SenderType senderType, List<PersonInfo> toInfos, PersonInfo fromInfo
            , String subject, String content, Boolean isUseHtml
            , String templateFileName , Map<String, Object> params, List<AttachFile> attachFiles) {
        super(senderType);
        this.idx = idx;
        this.toInfos = toInfos;
        this.fromInfo = fromInfo;
        this.subject = subject;
        this.content = content;
        this.isUseHtml = isUseHtml;
        this.templateFileName = templateFileName;
        this.params = params;
        this.attachFiles = attachFiles;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PersonInfo {
        private String name;
        private String address;

        @Builder
        public PersonInfo(String name, String address) {
            this.name = name;
            this.address = address;
        }
    }

    @Getter
    public static class AttachFile {
        private String fileName;            // 저장 파일 명
        private String originalFileName;    // 원본 파일 명
        private String filePath;            // 파일 경로
        private String fileExtension;       // 파일 확장자

        @Builder
        public AttachFile(String fileName, String originalFileName, String filePath, String fileExtension) {
            this.fileName = fileName;
            this.originalFileName = originalFileName;
            this.filePath = filePath;
            this.fileExtension = fileExtension;
        }
    }
}
