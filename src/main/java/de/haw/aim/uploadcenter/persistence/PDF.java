package de.haw.aim.uploadcenter.persistence;

import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PDF implements UploadedFile
{

    @Id
    protected String id;

    protected String name;

    protected String location;

    @Autowired
    private Environment env;

    public PDF()
    {
    }

    public PDF(String filepath)
    {
        this.location = filepath;
        this.name = this.location.substring(0, this.location.length() - 4);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    @Override
    public String getVendorId() {
        String[] filepath = location.split("/");
        return  filepath[filepath.length - 2];
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "PDF{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public void validate() throws ValueDoesntValidateToConfigFileException
    {
        String fileTypes;
        try
        {
            fileTypes = env.getProperty("uploadcenter.pdf.filetypes");
        } catch (NullPointerException e)
        {
            fileTypes = "pdf";
        }

        if (!this.location.endsWith("." + fileTypes))
        {
            throw new ValueDoesntValidateToConfigFileException("Wrong file ending");
        }
    }
}
