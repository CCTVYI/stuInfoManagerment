/**
 * 业务层接口，用于应用层与业务层之间操作类
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.factory;

import cn.sitcat.service.IStu_gradesService;
import cn.sitcat.service.IStu_infoService;
import cn.sitcat.service.impl.Stu_infoServiceImpl;
import cn.sitcat.service.impl.Stu_gradesServiceImpl;

public class ServiceFacory {
        public static IStu_infoService getIStu_infoServiceInstance(){
            return new Stu_infoServiceImpl();
        }

        public static IStu_gradesService getIStu_gradesServiceInstance(){
            return new Stu_gradesServiceImpl();
        }
}
