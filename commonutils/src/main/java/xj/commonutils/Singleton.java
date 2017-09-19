package xj.commonutils;

/**
 * @author meitu.xujun  on 2017/9/13
 * @version 0.1
 */
public interface Singleton {

     void init();

   boolean isInitialized();

    Object getLock();
}
