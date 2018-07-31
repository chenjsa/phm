package com.pms.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheMgr {  
    
    private static Map<String,String> cacheMap = new HashMap<String,String>();  
//    private static Map cacheConfMap = new HashMap();  
      
    private static CacheMgr cm = null;  
      
    //构造方法  
    private CacheMgr(){  
    }  
      
     
      
    public static CacheMgr getInstance(){  
         if(cm==null){  
          cm = new CacheMgr();  
          Thread t = new ClearCache();  
          t.start();  
         }  
         return cm;  
        }  
      
      
    /** 
     * 增加缓存 
     * @param key 
     * @param value 
     * @param ccm 缓存对象 
     * @return  
     */  
    public  boolean addCache(String key,String value){  
         System.out.println("开始增加缓存－－－－－－－－－－－－－");  
         boolean flag = false;  
         try {  
             cacheMap.put(key, value);  
//             cacheConfMap.put(key, ccm);  
             System.out.println("增加缓存结束－－－－－－－－－－－－－");  
             System.out.println("now addcache=="+cacheMap.size());  
             flag=true;  
         } catch (Exception e) {  
           e.printStackTrace();  
         }  
           
         return flag;  
        }  
      
      
      /** 
       * 获取缓存实体 
       */  
    public Object getValue(String key){  
        Object ob=cacheMap.get(key);  
        if(ob!=null){  
            return ob;  
        }else{  
            return null;  
        }  
    }  
      
      
       /** 
        * 获取缓存数据的数量 
        * @return 
        */  
       public int getSize(){  
           return cacheMap.size();  
       }  
      
         
         
      
    /** 
     * 删除缓存 
     * @param key 
     * @return  
     */  
    public  boolean removeCache(Object key){  
        boolean flag=false;  
        try {  
             cacheMap.remove(key);  
//             cacheConfMap.remove(key);  
             flag=true;  
           } catch (Exception e) {  
               e.printStackTrace();  
           }  
         return flag;  
        }  
      
      
      
    /** 
     * 清除缓存的类 
     * 继承Thread线程类 
     */  
    private static class ClearCache extends Thread{  
         public void run(){
        	 cacheMap.clear();
//        	 Set keSet=cacheMap.keySet();
//        	 while(keSet.){  
//                 Object key = keSet.next();  
//                 cacheMap.remove(key);  
//                 cacheConfMap.remove(key);  
//                   
//                } 
////        	 
//        	 
//          while(true){  
//           Set tempSet = new HashSet();  
//           Set set = cacheConfMap.keySet();  
//           Iterator it = set.iterator();  
//           while(it.hasNext()){  
//            Object key = it.next();  
//            CacheConfModel ccm = (CacheConfModel)cacheConfMap.get(key);  
//            //比较是否需要清除  
//            if(!ccm.isForever()){  
//             if((new Date().getTime()-ccm.getBeginTime())>= ccm.getDurableTime()*60*1000){  
//              //可以清除，先记录下来  
//              tempSet.add(key);  
//             }  
//            }  
//           }  
//           //真正清除  
//           Iterator tempIt = tempSet.iterator();  
//           while(tempIt.hasNext()){  
//            Object key = tempIt.next();  
//            cacheMap.remove(key);  
//            cacheConfMap.remove(key);  
//              
//           }  
//           System.out.println("now thread================>"+cacheMap.size());  
//           //休息  
//           try {  
//            Thread.sleep(60*1000L);  
//           } catch (InterruptedException e) {  
//            e.printStackTrace();  
//           }  
//          }  
         }  
        }
      
      
      
      
}  
