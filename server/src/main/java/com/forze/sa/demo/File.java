package com.forze.sa.demo;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class File {
    @Id @GeneratedValue
    private @NonNull Long id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] rawFile;

    public File(String fileName, String fileType, byte[] rawFile) {
        this.fileType = fileType;
        this.fileName = fileName;
        this.rawFile = rawFile;
    }

    public String getName() {
        return this.fileName;
    }

    public byte[] getPic() {
        return this.rawFile;
    }
}
