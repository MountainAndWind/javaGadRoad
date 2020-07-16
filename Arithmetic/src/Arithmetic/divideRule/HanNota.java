package Arithmetic.divideRule;

public class HanNota {
	private int i = 1;
	public void hanNota(int n,char from,char dependOn,char to){
		if(n == 1){//圆盘只有一个时，只需将其从A塔移到C塔
			move(1,from,to);
		}else{
			hanNota(n-1,from,to,dependOn);//递归，把A塔上编号~n-1的圆盘移到B上，以C为辅助塔
			move(n, from, to);//把A塔上编号为n的圆盘移到C上
			hanNota(n-1,dependOn,from,to);//递归，把B塔上编号~n-1的圆盘移到C上，以A为辅助塔
		}
	}

	private void move(int n, char from, char to) {
		System.out.println("第"+i+++"步从"+from+"------>"+to);
	}

	public static void main(String [] args){
		HanNota hanNota = new HanNota();
		hanNota.hanNota(72, 'A', 'B', 'C');
	}
}
