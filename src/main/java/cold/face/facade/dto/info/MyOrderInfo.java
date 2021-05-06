package cold.face.facade.dto.info;

import io.swagger.annotations.ApiModel;

/**
 * @author lijing_yan
 * @data 2021-04-28 16:42
 */
@ApiModel
public class MyOrderInfo {
    int userId;
    int orderId;
    long createTime;
    int number;
    int goodsId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "MyOrderInfo{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                ", number=" + number +
                ", goodsId=" + goodsId +
                '}';
    }
}
