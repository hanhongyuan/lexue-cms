package com.lexue.base.invokelink;

/**
 * Created by zhao.weiwei on 2017/6/9.
 */
public class ThreadLocalContext {
    private static ThreadLocal<String> invokeLinkId = new ThreadLocal<>();
    private static ThreadLocal<String> invokeLinkCount = new ThreadLocal<>();


    public static void setInvokeLinkId(String id){
        invokeLinkId.set(id);
    }
    public static String getInvokeLinkId(){
        return invokeLinkId.get();
    }

    public static void setInvokeLinkCount(String count){
        invokeLinkCount.set(count);
    }
    public static String getInvokeLinkCount(){
        return invokeLinkCount.get();
    }

}
