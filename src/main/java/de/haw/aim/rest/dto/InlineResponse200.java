package de.haw.aim.rest.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




/**
 * InlineResponse200
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

public class InlineResponse200   {
  private LoginResponse loginResponse = null;

  private String anbieterInfoId = null;

  private List<String> produktInfoIds = new ArrayList<String>();

  public InlineResponse200 user(LoginResponse loginResponse) {
    this.loginResponse = loginResponse;
    return this;
  }

   /**
   * Get loginResponse
   * @return loginResponse
  **/
  @ApiModelProperty(value = "")
  public LoginResponse getLoginResponse() {
    return loginResponse;
  }

  public void setLoginResponse(LoginResponse loginResponse) {
    this.loginResponse = loginResponse;
  }

  public InlineResponse200 anbieterInfoId(String anbieterInfoId) {
    this.anbieterInfoId = anbieterInfoId;
    return this;
  }

   /**
   * Get anbieterInfoId
   * @return anbieterInfoId
  **/
  @ApiModelProperty(value = "")
  public String getAnbieterInfoId() {
    return anbieterInfoId;
  }

  public void setAnbieterInfoId(String anbieterInfoId) {
    this.anbieterInfoId = anbieterInfoId;
  }

  public InlineResponse200 produktInfoIds(List<String> produktInfoIds) {
    this.produktInfoIds = produktInfoIds;
    return this;
  }

  public InlineResponse200 addProduktInfoIdsItem(String produktInfoIdsItem) {
    this.produktInfoIds.add(produktInfoIdsItem);
    return this;
  }

   /**
   * Get produktInfoIds
   * @return produktInfoIds
  **/
  @ApiModelProperty(value = "")
  public List<String> getProduktInfoIds() {
    return produktInfoIds;
  }

  public void setProduktInfoIds(List<String> produktInfoIds) {
    this.produktInfoIds = produktInfoIds;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200 inlineResponse200 = (InlineResponse200) o;
    return Objects.equals(this.loginResponse, inlineResponse200.loginResponse) &&
        Objects.equals(this.anbieterInfoId, inlineResponse200.anbieterInfoId) &&
        Objects.equals(this.produktInfoIds, inlineResponse200.produktInfoIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loginResponse, anbieterInfoId, produktInfoIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    loginResponse: ").append(toIndentedString(loginResponse)).append("\n");
    sb.append("    anbieterInfoId: ").append(toIndentedString(anbieterInfoId)).append("\n");
    sb.append("    produktInfoIds: ").append(toIndentedString(produktInfoIds)).append("\n");
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

