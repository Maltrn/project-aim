package de.haw.aim.rest.dto;

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

import java.util.*;


/**
 * InfoDTO
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

public class InfoDTO implements Validatable
{

    @Autowired
    IUploadCenter uploadCenterInterface;

    @Autowired
    private Environment env;

    private String id = null;

    private String name = null;

    private String shortDescription = null;

    private String longDescription = null;

    private String mainPic = null;

    private List<String> fileGallery = new ArrayList<String>();

    private List<Map<String, String>> facts = new ArrayList<>();

    public InfoDTO id(String id)
    {
        this.id = id;
        return this;
    }

    public ProductInfo convertToProductInfo()
    {
        Picture mainPic = (Picture) uploadCenterInterface.findById(this.getMainPic());

        List<UploadedFile> fileGallery = new ArrayList<>();
        for (String s : this.fileGallery)
        {
            fileGallery.add(uploadCenterInterface.findById(s));
        }

        List<Fact> facts = new ArrayList<>();
        for (Map<String, String> map : this.facts)
        {
            Map.Entry<String, String> entry = map.entrySet().iterator().next();
            facts.add(new Fact(entry.getKey(), entry.getValue()));
        }

        ProductInfo retVal = new ProductInfo(
                this.getName(),
                this.getShortDescription(),
                this.getLongDescription(),
                mainPic,
                fileGallery,
                facts
        );

        return retVal;
    }

    public VendorInfo convertToVendorInfo()
    {
        Picture mainPic = (Picture) uploadCenterInterface.findById(this.getMainPic());

        List<UploadedFile> fileGallery = new ArrayList<>();
        for (String s : this.fileGallery)
        {
            fileGallery.add(uploadCenterInterface.findById(s));
        }

        List<Fact> facts = new ArrayList<>();
        for (Map<String, String> map : this.facts)
        {
            Map.Entry<String, String> entry = map.entrySet().iterator().next();
            facts.add(new Fact(entry.getKey(), entry.getValue()));
        }

        VendorInfo retVal = new VendorInfo(
                this.getName(),
                this.getShortDescription(),
                this.getLongDescription(),
                mainPic,
                fileGallery,
                facts
        );

        return retVal;
    }

    public static InfoDTO from(Info info)
    {
        InfoDTO retVal = new InfoDTO();

        retVal.setId(info.getId());
        retVal.setName(info.getName());
        retVal.setShortDescription(info.getShortDescription());
        retVal.setLongDescription(info.getLongDescription());
        retVal.setMainPic(info.getMainPic().getId());

        List<String> fileIdList = new ArrayList<>();
        for (UploadedFile f : info.getFileGallery())
        {
            fileIdList.add(f.getId());
        }
        retVal.setFileGallery(fileIdList);

        List<Map<String, String>> facts = new ArrayList();
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
    @ApiModelProperty(value = "")
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
    @ApiModelProperty(value = "")
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
    @ApiModelProperty(value = "")
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
    @ApiModelProperty(value = "")
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
    @ApiModelProperty(value = "")
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
    @ApiModelProperty(value = "")
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
    @ApiModelProperty(value = "")
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

        if (this.getShortDescription().length() > shortDescriptionMaxLength)
        {
            throw new ValueDoesntValidateToConfigFileException("short description is too long");
        }
        if (this.getLongDescription().length() > longDescriptionMaxLength)
        {
            throw new ValueDoesntValidateToConfigFileException("long description is too long");
        }

    }

}

