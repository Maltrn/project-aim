package de.haw.aim.importer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VendorDTO
{
    String id;
    String label;
    List<ProductDTO> produkts;

    public VendorDTO()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<ProductDTO> getProdukts()
    {
        return produkts;
    }

    public void setProdukts(List<ProductDTO> produkts)
    {
        this.produkts = produkts;
    }

    @Override
    public String toString()
    {
        return "VendorDTO{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", produkts=" + produkts +
                '}';
    }
}
