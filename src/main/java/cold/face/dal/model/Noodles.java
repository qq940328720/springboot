package cold.face.dal.model;

import java.math.BigDecimal;

public class Noodles {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column noodles.id
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column noodles.noodles_name
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    private String noodlesName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column noodles.type_code
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    private String typeCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column noodles.price
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column noodles.remark
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column noodles.id
     *
     * @return the value of noodles.id
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column noodles.id
     *
     * @param id the value for noodles.id
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column noodles.noodles_name
     *
     * @return the value of noodles.noodles_name
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public String getNoodlesName() {
        return noodlesName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column noodles.noodles_name
     *
     * @param noodlesName the value for noodles.noodles_name
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public void setNoodlesName(String noodlesName) {
        this.noodlesName = noodlesName == null ? null : noodlesName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column noodles.type_code
     *
     * @return the value of noodles.type_code
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column noodles.type_code
     *
     * @param typeCode the value for noodles.type_code
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column noodles.price
     *
     * @return the value of noodles.price
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column noodles.price
     *
     * @param price the value for noodles.price
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column noodles.remark
     *
     * @return the value of noodles.remark
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column noodles.remark
     *
     * @param remark the value for noodles.remark
     *
     * @mbg.generated Wed Jul 18 09:50:24 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}