package cold.face.facade.service;

import cold.face.facade.dto.info.MyOrderInfo;

public interface MyAQSService {
    int doWith(MyOrderInfo info);

    void fresh();
}
