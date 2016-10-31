package de.haw.aim.rest.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;




/**
 * Info
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

public class Info   {
  private String id = null;

  private String name = null;

  private String shortDescription = null;

  private String longDescription = null;

  private String mainPic = null;

  private List<String> fileGallery = new ArrayList<String>();

  private List<Object> facts = new ArrayList<Object>();

  public Info id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Info name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Info shortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }

   /**
   * Get shortDescription
   * @return shortDescription
  **/
  @ApiModelProperty(value = "")
  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public Info longDescription(String longDescription) {
    this.longDescription = longDescription;
    return this;
  }

   /**
   * Get longDescription
   * @return longDescription
  **/
  @ApiModelProperty(value = "")
  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public Info mainPic(String mainPic) {
    this.mainPic = mainPic;
    return this;
  }

   /**
   * Get mainPic
   * @return mainPic
  **/
  @ApiModelProperty(value = "")
  public String getMainPic() {
    return mainPic;
  }

  public void setMainPic(String mainPic) {
    this.mainPic = mainPic;
  }

  public Info fileGallery(List<String> fileGallery) {
    this.fileGallery = fileGallery;
    return this;
  }

  public Info addFileGalleryItem(String fileGalleryItem) {
    this.fileGallery.add(fileGalleryItem);
    return this;
  }

   /**
   * Get fileGallery
   * @return fileGallery
  **/
  @ApiModelProperty(value = "")
  public List<String> getFileGallery() {
    return fileGallery;
  }

  public void setFileGallery(List<String> fileGallery) {
    this.fileGallery = fileGallery;
  }

  public Info facts(List<Object> facts) {
    this.facts = facts;
    return this;
  }

  public Info addFactsItem(Object factsItem) {
    this.facts.add(factsItem);
    return this;
  }

   /**
   * Get facts
   * @return facts
  **/
  @ApiModelProperty(value = "")
  public List<Object> getFacts() {
    return facts;
  }

  public void setFacts(List<Object> facts) {
    this.facts = facts;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Info info = (Info) o;
    return Objects.equals(this.id, info.id) &&
        Objects.equals(this.name, info.name) &&
        Objects.equals(this.shortDescription, info.shortDescription) &&
        Objects.equals(this.longDescription, info.longDescription) &&
        Objects.equals(this.mainPic, info.mainPic) &&
        Objects.equals(this.fileGallery, info.fileGallery) &&
        Objects.equals(this.facts, info.facts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, shortDescription, longDescription, mainPic, fileGallery, facts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Info {\n");
    
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
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

