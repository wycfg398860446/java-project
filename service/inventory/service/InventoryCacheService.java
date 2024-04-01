package service.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 库存扣减实现类,这里节省了接口定义直接编写实现类
 */
@Service
public class InventoryCacheService {

    private final StringRedisTemplate<String, Object> stringRedisTemplate;

    @Autowired
    public InventoryCacheService(StringRedisTemplate<String, Object> stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 扣减库存，返回扣减后的结果
    public boolean decreaseInventory(String productId, int quantity) {
        // 从Redis中获取当前库存数量
        Integer currentStock = stringRedisTemplate.opsForValue().get(productId);
        if (currentStock == null) {
            // 如果库存不存在，返回失败
            return false;
        }
        if (currentStock < quantity) {
            // 如果库存数量小于要扣减的数量，返回失败
            return false;
        }
        // 使用Lua脚本确保原子性操作
        String luaScript = "local newStock = tonumber(ARGV[1]) - tonumber(ARGV[2]) " +
                "return redis.call('SET', KEYS[1], newStock)";
        stringRedisTemplate.execute(luaScript, Collections.singletonList(productId), currentStock - quantity);
        return true;
    }

    // 检查库存是否足够，如果足够则扣减并返回结果
    public boolean checkAndDecreaseInventory(String productId, int quantity) {
        // 使用Lua脚本确保原子性操作
        String luaScript = "if tonumber(KEYS[1]) >= tonumber(ARGV[1]) then " +
                "return redis.call('DECRBY', KEYS[1], ARGV[1]) " +
                "else " +
                "return 0 " +
                "end";
        Long result = (Long) stringRedisTemplate.execute(luaScript, Collections.singletonList(productId), quantity);
        return result != null && result > 0;
    }
}