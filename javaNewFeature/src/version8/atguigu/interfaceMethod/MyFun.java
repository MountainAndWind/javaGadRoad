package version8.atguigu.interfaceMethod;

public interface MyFun {
	
	default String getName(){
		return "哈哈哈";
	}

	Integer getValue(Integer num);
}
