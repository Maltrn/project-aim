package de.haw.aim.uploadcenter.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class File {
    @Id
    private String id;
}
