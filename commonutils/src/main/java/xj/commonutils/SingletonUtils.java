package xj.commonutils;

/**
 * @author meitu.xujun  on 2017/8/31 16:20
 * @version 0.1
 */

public abstract class SingletonUtils<T> {

    private   volatile    T instance;

    protected  abstract  T newInstance();

    public  final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
