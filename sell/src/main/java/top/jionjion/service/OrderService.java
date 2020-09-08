package top.jionjion.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.jionjion.dto.OrderDTO;

/**
 *  @author Jion
 *      订单相关的服务
 */
public interface OrderService {

    /** 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询订单个数 */
    OrderDTO findOne(String orderId);

    /** 查询订单列表 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完成订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);
}