package cold.face.adapter.impl;

import cold.face.dal.dao.GoodsNumberDao;
import cold.face.dal.dao.OrderDao;
import cold.face.dal.model.GoodsNumber;
import cold.face.dal.model.Order;
import cold.face.facade.dto.info.MyOrderInfo;
import cold.face.facade.service.MyAQSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijing_yan
 * @data 2021-04-28 16:56
 */
@Service
public class MyAQSServiceImpl implements MyAQSService {

    private Logger LOG = LoggerFactory.getLogger(MyAQSServiceImpl.class);

    @Autowired
    OrderDao orderDao;

    @Autowired
    GoodsNumberDao goodsNumberDao;

    private static AtomicInteger number = new AtomicInteger(10);
//    private static volatile int number = 10;

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    @Override
    public int doWith(MyOrderInfo info) {
//        int num = GoodsNumber.countDown(info.getGoodsId());
        int num = this.countDown(info);
//        int num = this.countDown_update(info);
//        int num = this.countDown_synchronized(info);
        List<String> list = new ArrayList<>();

        

        if (num == -1) {
            LOG.error("商品错误:" + num + ":" + info.toString());
        } else if (num == 0) {
            LOG.error("库存不足:" + num + ":" + info.toString());
        } else {
            LOG.error("库存剩余:" + num + ":" + info.toString());
        }
        return 0;
    }

    /**
     * synchronized悲观锁：代码块加锁
     * 保证线程安全：在同一时刻，只能有一个线程可以执行代码块中的代码。
     * 重量级的操作，不仅是因为加锁需要消耗额外的资源，还因为线程状态的切换会涉及操作系统核心态和用户态的转换；
     * 随着JVM对锁进行的一系列优化(如自旋锁、轻量级锁、锁粗化等)，synchronized的性能表现已经越来越好。
     * <p>
     * begin()
     * update orders set count = count -1 where count > 0 and sku_id = '123'
     * commit()
     *
     * @param info
     * @return
     */
    private synchronized int countDown_synchronized(MyOrderInfo info) {
        GoodsNumber goodsNumber = goodsNumberDao.selectByPrimaryKey(info.getGoodsId());
        if (goodsNumber != null) {
            int num = goodsNumber.getGoodsNumber();
            if (num > 0) {
                num--;
                goodsNumber.setGoodsNumber(num);
                goodsNumberDao.updateByPrimaryKey(goodsNumber);
                Order order = new Order();
                order.setCreateTime(info.getCreateTime());
                order.setGoodsId(info.getGoodsId());
                order.setNumber(info.getNumber());
                order.setUserId(info.getUserId());
                order.setOrderId(info.getOrderId());
                orderDao.insert(order);
                return num;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    private int countDown(MyOrderInfo info) {
        if (number.intValue() > 0) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number.getAndDecrement();
            Order order = new Order();
            order.setCreateTime(info.getCreateTime());
            order.setGoodsId(info.getGoodsId());
            order.setNumber(info.getNumber());
            order.setUserId(info.getUserId());
            order.setOrderId(info.getOrderId());
            orderDao.insert(order);
            return number.intValue();
        } else {
            return 0;
        }
    }

    /**
     * 数据库悲观锁：采用排他锁
     * 当用户同时到达更新操作，同时到达的用户一个个执行
     * 在当前这个update语句commit之前，其他用户等待执行
     * <p>
     * begin()
     * update orders set count = count -1 where count > 0 and sku_id = '123'
     * commit()
     *
     * @param info
     * @return
     */
    private int countDown_update(MyOrderInfo info) {
        GoodsNumber goodsNumber = new GoodsNumber();
        goodsNumber.setGoodsNumber(info.getNumber());
        goodsNumber.setGoodsId(info.getGoodsId());
        int row = goodsNumberDao.countDownGoodsNumber(goodsNumber);
        if (row == 0) {
            return 0;
        } else {
            Order order = new Order();
            order.setCreateTime(info.getCreateTime());
            order.setGoodsId(info.getGoodsId());
            order.setNumber(info.getNumber());
            order.setUserId(info.getUserId());
            order.setOrderId(info.getOrderId());
            orderDao.insert(order);
            return row;
        }
    }

    @Override
    public void fresh() {
        this.number.getAndSet(10);
        GoodsNumberCache.fresh();
    }
}