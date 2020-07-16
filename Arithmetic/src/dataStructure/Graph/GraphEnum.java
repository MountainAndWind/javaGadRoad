package dataStructure.Graph;

/**
 * 图的枚举类
 */
public enum GraphEnum {

    IN(1),
    OUT(0);

    private int code;
    GraphEnum(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
