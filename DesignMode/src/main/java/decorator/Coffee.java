package decorator;

/**
 *  开发类中间内.
 * @author Jion
 */
public class Coffee extends Drink{
    /** 计算价格方法 */
    @Override
    public Double cose() {
        return super.getPrice();
    }
}
