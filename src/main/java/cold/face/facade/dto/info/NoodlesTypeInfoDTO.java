package cold.face.facade.dto.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "面条分类信息实体")
public class NoodlesTypeInfoDTO {

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "分类编码", required = true)
    private String typeCode;

    @ApiModelProperty(value = "分类名称", required = true)
    private String name;

    @ApiModelProperty(value = "上级分类编码")
    private String parentTypeCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentTypeCode() {
        return parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "NoodlesTypeInfoDTO{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", name='" + name + '\'' +
                ", parentTypeCode='" + parentTypeCode + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}