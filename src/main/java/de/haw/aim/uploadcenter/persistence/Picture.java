package de.haw.aim.uploadcenter.persistence;

import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Picture implements UploadedFile
{

    @Id
    protected String id;

    protected String name;

    protected String location;

    @Autowired
    protected Environment environment;

    public Picture()
    {
    }

    public Picture(String filepath)
    {
        this.location = filepath;
        this.name = this.location.substring(0, this.location.length() - 4);
    }

    @Override
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "Picture{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public void validate() throws ValueDoesntValidateToConfigFileException
    {
        boolean valid = false;
        String fileTypes;

        try
        {
            fileTypes = this.environment.getProperty("uploadcenter.pictures.filetypes");
        } catch (NullPointerException e)
        {
            fileTypes = "jpeg,gif,png,jpg";
        }

        for (String fileType : fileTypes.split(","))
        {
            if (this.location.endsWith("." + fileType))
            {
                valid = true;
            }
        }
        if (!valid)
        {
            throw new ValueDoesntValidateToConfigFileException("Wrong file ending");
        }
    }
}
