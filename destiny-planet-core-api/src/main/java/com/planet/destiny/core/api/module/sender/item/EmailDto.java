package com.planet.destiny.core.api.module.sender.item;

import com.planet.destiny.core.api.constant.SenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailDto extends SenderDto implements Serializable {
    private Long idx;
    private PersonInfo toInfo;              // 보내는 사람 정보
    private PersonInfo fromInfo;            // 받는 사람 정보
    private String subject;                 // 제목
    private String content;                 // 내용
    private Boolean isUseTemplate = true;   // template파일 사용 여부
    private String templateFileName;        // 파일명(확장파 포함)
    private Map<String, Object> params;     // 템플릿 파일 파라미터
    private List<AttachFile> attachFiles;   // 첨부 파일


    @Builder
    public EmailDto(SenderType senderType, PersonInfo toInfo, PersonInfo fromInfo
            , String subject, String content, Boolean isUseTemplate
            , String templateFileName , Map<String, Object> params, List<AttachFile> attachFiles, Long creatorIdx) {
        super(senderType);
        this.toInfo = toInfo;
        this.fromInfo = fromInfo;
        this.subject = subject;
        this.content = content;
        this.isUseTemplate = isUseTemplate;
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
