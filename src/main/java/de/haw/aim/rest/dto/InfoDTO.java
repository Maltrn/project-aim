package de.haw.aim.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.haw.aim.uploadcenter.facade.IUploadCenter;
import de.haw.aim.uploadcenter.persistence.Picture;
import de.haw.aim.uploadcenter.persistence.UploadedFile;
import de.haw.aim.validator.Validatable;
import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import de.haw.aim.vendor.persistence.Fact;
import de.haw.aim.vendor.persistence.Info;
import de.haw.aim.vendor.persistence.ProductInfo;
import de.haw.aim.vendor.persistence.VendorInfo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InfoDTO implements Validatable
{
    @Autowired
    IUploadCenter iUploadCenter;

    @Autowired
    private Environment env;

    private String id;

    private String name;

    private String shortDescription;

    private String longDescription;

    private String mainPic;

    private List<String> fileGallery = new ArrayList<>();

    private List<Map<String, String>> facts = new ArrayList<>();

    @JsonIgnore
    public void setiUploadCenter(IUploadCenter iUploadCenter)
    {
        this.iUploadCenter = iUploadCenter;
    }

    public ProductInfo convertToProductInfo()
    {
        Picture mainPicFile = null;
        if(mainPic != null){
            mainPicFile = (Picture) iUploadCenter.findById(this.mainPic);
        }

        List<UploadedFile> fileGallery = new ArrayList<>();
        for (String s : this.fileGallery)
        {
            fileGallery.add(iUploadCenter.findById(s));
        }

        List<Fact> facts = new ArrayList<>();
        for (Map<String, String> map : this.facts)
        {
            Map.Entry<String, String> entry = map.entrySet().iterator().next();
            facts.add(new Fact(entry.getKey(), entry.getValue()));
        }

        return new ProductInfo(
                this.getId(),
                this.getName(),
                this.getShortDescription(),
                this.getLongDescription(),
                mainPicFile,
                fileGallery,
                facts
        );
    }

    public VendorInfo convertToVendorInfo()
    {
        Picture mainPicFile = null;
        if(mainPic != null){
            mainPicFile = (Picture) iUploadCenter.findById(this.mainPic);
        }

        List<UploadedFile> fileGallery = new ArrayList<>();
        for (String s : this.fileGallery)
        {
            fileGallery.add(iUploadCenter.findById(s));
        }

        List<Fact> facts = new ArrayList<>();
        for (Map<String, String> map : this.facts)
        {
            Map.Entry<String, String> entry = map.entrySet().iterator().next();
            facts.add(new Fact(entry.getKey(), entry.getValue()));
        }

        return new VendorInfo(
                this.getId(),
                this.getName(),
                this.getShortDescription(),
                this.getLongDescription(),
                mainPicFile,
                fileGallery,
                facts
        );
    }

    public static InfoDTO from(Info info)
    {
        InfoDTO retVal = new InfoDTO();

        retVal.setId(info.getId());
        retVal.setName(info.getName());
        retVal.setShortDescription(info.getShortDescription());
        retVal.setLongDescription(info.getLongDescription());
        if(info.getMainPic() != null)
        {
            retVal.setMainPic(info.getMainPic().getId());
        }

        List<String> fileIdList = new ArrayList<>();
        for (UploadedFile f : info.getFileGallery())
        {
            fileIdList.add(f.getId());
        }
        retVal.setFileGallery(fileIdList);

        List<Map<String, String>> facts = new ArrayList<>();
        for (Fact f : info.getFacts())
        {
            Map<String, String> factMap = new HashMap<>();
            factMap.put(f.getKey(), f.getValue());
            facts.add(factMap);
        }
        retVal.setFacts(facts);

        return retVal;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty()
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public InfoDTO name(String name)
    {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty()
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public InfoDTO shortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
        return this;
    }

    /**
     * Get shortDescription
     *
     * @return shortDescription
     **/
    @ApiModelProperty()
    public String getShortDescription()
    {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public InfoDTO longDescription(String longDescription)
    {
        this.longDescription = longDescription;
        return this;
    }

    /**
     * Get longDescription
     *
     * @return longDescription
     **/
    @ApiModelProperty()
    public String getLongDescription()
    {
        return longDescription;
    }

    public void setLongDescription(String longDescription)
    {
        this.longDescription = longDescription;
    }

    public InfoDTO mainPic(String mainPic)
    {
        this.mainPic = mainPic;
        return this;
    }

    /**
     * Get mainPic
     *
     * @return mainPic
     **/
    @ApiModelProperty()
    public String getMainPic()
    {
        return mainPic;
    }

    public void setMainPic(String mainPic)
    {
        this.mainPic = mainPic;
    }

    public InfoDTO fileGallery(List<String> fileGallery)
    {
        this.fileGallery = fileGallery;
        return this;
    }

    public InfoDTO addFileGalleryItem(String fileGalleryItem)
    {
        this.fileGallery.add(fileGalleryItem);
        return this;
    }

    /**
     * Get fileGallery
     *
     * @return fileGallery
     **/
    @ApiModelProperty()
    public List<String> getFileGallery()
    {
        return fileGallery;
    }

    public void setFileGallery(List<String> fileGallery)
    {
        this.fileGallery = fileGallery;
    }

    public InfoDTO facts(List<Map<String, String>> facts)
    {
        this.facts = facts;
        return this;
    }

    public InfoDTO addFactsItem(Map<String, String> factsItem)
    {
        this.facts.add(factsItem);
        return this;
    }

    /**
     * Get facts
     *
     * @return facts
     **/
    @ApiModelProperty()
    public List<Map<String, String>> getFacts()
    {
        return facts;
    }

    public void setFacts(List<Map<String, String>> facts)
    {
        this.facts = facts;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        InfoDTO infoDTO = (InfoDTO) o;
        return Objects.equals(this.id, infoDTO.id) &&
                Objects.equals(this.name, infoDTO.name) &&
                Objects.equals(this.shortDescription, infoDTO.shortDescription) &&
                Objects.equals(this.longDescription, infoDTO.longDescription) &&
                Objects.equals(this.mainPic, infoDTO.mainPic) &&
                Objects.equals(this.fileGallery, infoDTO.fileGallery) &&
                Objects.equals(this.facts, infoDTO.facts);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, shortDescription, longDescription, mainPic, fileGallery, facts);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class InfoDTO {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    shortDescription: ").append(toIndentedString(shortDescription)).append("\n");
        sb.append("    longDescription: ").append(toIndentedString(longDescription)).append("\n");
        sb.append("    mainPic: ").append(toIndentedString(mainPic)).append("\n");
        sb.append("    fileGallery: ").append(toIndentedString(fileGallery)).append("\n");
        sb.append("    facts: ").append(toIndentedString(facts)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object from string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o)
    {
        if (o == null)
        {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Override
    public void validate() throws ValueDoesntValidateToConfigFileException
    {
        int shortDescriptionMaxLength;
        int longDescriptionMaxLength;

        try
        {
            longDescriptionMaxLength = Integer.valueOf(env.getProperty("info.longdescription.maxlength"));
            shortDescriptionMaxLength = Integer.valueOf(env.getProperty("info.shortdescription.maxlength"));
        } catch (NullPointerException e)
        {
            shortDescriptionMaxLength = 30;
            longDescriptionMaxLength = 30;
        }

        if (this.shortDescription == null || this.getShortDescription().length() > shortDescriptionMaxLength)
        {
            throw new ValueDoesntValidateToConfigFileException("short description is too long");
        }
        if (this.longDescription == null || this.getLongDescription().length() > longDescriptionMaxLength)
        {
            throw new ValueDoesntValidateToConfigFileException("long description is too long");
        }

    }
}

