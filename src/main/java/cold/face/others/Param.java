package cold.face.others;

import java.util.Date;

/**
 * 公共参数
 * @author Administrator
 *
 */
public class Param {
	/**
	 * 请求的时间，用于校验请求是否过期
	 */
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
