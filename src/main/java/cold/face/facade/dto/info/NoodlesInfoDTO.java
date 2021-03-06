package cold.face.facade.dto.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "面条信息实体")
public class NoodlesInfoDTO {
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "名称", required = true)
    private String noodlesName;

    @ApiModelProperty(value = "分类编码", required = true)
    private String typeCode;

    @ApiModelProperty(value = "价格(元/碗)", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoodlesName() {
        return noodlesName;
    }

    public void setNoodlesName(String noodlesName) {
        this.noodlesName = noodlesName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "NoodlesInfoDTO{" +
                "id=" + id +
                ", noodlesName='" + noodlesName + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                '}';
    }
}
