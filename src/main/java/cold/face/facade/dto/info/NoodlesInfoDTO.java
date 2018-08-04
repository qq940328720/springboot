package cold.face.facade.dto.info;

public class NoodlesInfoDTO {
    private String noodlesName;
    private String noodlesType;

    public String getNoodlesName() {
        return noodlesName;
    }

    public void setNoodlesName(String noodlesName) {
        this.noodlesName = noodlesName;
    }

    public String getNoodlesType() {
        return noodlesType;
    }

    public void setNoodlesType(String noodlesType) {
        this.noodlesType = noodlesType;
    }

    @Override
    public String toString() {
        return "NoodlesInfoDTO{" +
                "noodlesName='" + noodlesName + '\'' +
                ", noodlesType='" + noodlesType + '\'' +
                '}';
    }
}
