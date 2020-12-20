package ncu.im3069.demo.app;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class Helper {
	
	protected static Helper helper;
	
    /** 儲存JDBC資料庫連線 */
    protected Connection conn = null;
    
    /** 儲存JDBC預準備之SQL指令 */
    protected PreparedStatement pres = null;
	
//    /**
//     * 靜態方法<br>
//     * 實作Singleton（單例模式），僅允許建立一個Helper物件
//     * @return 
//     *
//     * @return the helper 回傳Helper物件
//     */
//    public abstract Helper getHelper();
//    {
//        /** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
//        if(helper == null) helper = new Helper();
//        
//        return helper;
//    }
}
