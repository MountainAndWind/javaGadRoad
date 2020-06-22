package version8.atguigu;

public interface MyFun {
	
	default String getName(){
		return "哈哈哈";
	}

	Integer getValue(Integer num);
}
