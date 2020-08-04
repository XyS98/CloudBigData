package com.sitech.cloudide.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>Title: MachineInfoEnum</p>
 * <p>Description: 机器信息枚举</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 思特奇 </p>
 * @author heweia
 * @version 1.0
 * @createtime 2019-4-9 上午9:08:34
 *
 */
public class MachineInfoEnum {
    
    /**
     * 
     * <p>Title: AvailableEnum</p>
     * <p>Description: 机器信息枚举</p>
     * <p>Copyright: Copyright (c) 2019</p>
     * <p>Company: 思特奇 </p>
     * @author heweia
     * @version 1.0
     * @createtime 2019-4-9 上午9:08:45
     *
     */
    public static enum AvailableEnum {
        YES(1),
        NO(0);

        private int value;

        private AvailableEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    
    /**
     * 
     * <p>Title: TypeEnum</p>
     * <p>Description: 机器类型</p>
     * <p>Copyright: Copyright (c) 2019</p>
     * <p>Company: 思特奇 </p>
     * @author heweia
     * @version 1.0
     * @createtime 2019-4-9 上午9:09:08
     *
     */
    public static enum TypeEnum {
        REDIS_NODE(0, "redis实例"),
        REDIS_MIGRATE_TOOL(2, "redis迁移工具");

        private int type;

        private String info;

        private static Map<Integer, TypeEnum> MAP = new HashMap<Integer, TypeEnum>();
        static {
            for (TypeEnum typeEnum : TypeEnum.values()) {
                MAP.put(typeEnum.getType(), typeEnum);
            }
        }

        public static TypeEnum getByType(int type) {
            return MAP.get(type);
        }

        private TypeEnum(int type, String info) {
            this.type = type;
            this.info = info;
        }
        public int getType() {
            return type;
        }
        public String getInfo() {
            return info;
        }


    }
    
}
