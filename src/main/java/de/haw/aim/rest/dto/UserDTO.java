package de.haw.aim.rest.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * UserDTO
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

public class UserDTO
{
    private LoginResponse loginResponse = null;

    private String vendorInfoId = null;

    private List<String> produktInfoIds = new ArrayList<>();

    public UserDTO(LoginResponse loginResponse, String vendorInfoId, List<String> produktInfoIds)
    {
        this.loginResponse = loginResponse;
        this.vendorInfoId = vendorInfoId;
        this.produktInfoIds = produktInfoIds;
    }

    public UserDTO user(LoginResponse loginResponse)
    {
        this.loginResponse = loginResponse;
        return this;
    }

    /**
     * Get loginResponse
     *
     * @return loginResponse
     **/
    @ApiModelProperty(value = "")
    public LoginResponse getLoginResponse()
    {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse)
    {
        this.loginResponse = loginResponse;
    }

    public UserDTO anbieterInfoId(String anbieterInfoId)
    {
        this.vendorInfoId = anbieterInfoId;
        return this;
    }

    /**
     * Get vendorInfoId
     *
     * @return vendorInfoId
     **/
    @ApiModelProperty(value = "")
    public String getVendorInfoId()
    {
        return vendorInfoId;
    }

    public void setVendorInfoId(String vendorInfoId)
    {
        this.vendorInfoId = vendorInfoId;
    }

    public UserDTO produktInfoIds(List<String> produktInfoIds)
    {
        this.produktInfoIds = produktInfoIds;
        return this;
    }

    public UserDTO addProduktInfoIdsItem(String produktInfoIdsItem)
    {
        this.produktInfoIds.add(produktInfoIdsItem);
        return this;
    }

    /**
     * Get produktInfoIds
     *
     * @return produktInfoIds
     **/
    @ApiModelProperty(value = "")
    public List<String> getProduktInfoIds()
    {
        return produktInfoIds;
    }

    public void setProduktInfoIds(List<String> produktInfoIds)
    {
        this.produktInfoIds = produktInfoIds;
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
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(this.loginResponse, userDTO.loginResponse) &&
                Objects.equals(this.vendorInfoId, userDTO.vendorInfoId) &&
                Objects.equals(this.produktInfoIds, userDTO.produktInfoIds);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(loginResponse, vendorInfoId, produktInfoIds);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDTO {\n");

        sb.append("    loginResponse: ").append(toIndentedString(loginResponse)).append("\n");
        sb.append("    vendorInfoId: ").append(toIndentedString(vendorInfoId)).append("\n");
        sb.append("    produktInfoIds: ").append(toIndentedString(produktInfoIds)).append("\n");
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
}

