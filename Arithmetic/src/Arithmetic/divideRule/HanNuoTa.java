package Arithmetic.divideRule;

/**
 * @description:��ŵ���㷨
 * ˼�룺�������ӣ���һ���������з����Ҵ��ϵ�����һ������ġ����ڽ����м����ӵ����������ӣ���Ҳ�Ǵ��ϵ�����һ�������
 * @author: slfang
 * @time: 2020/5/21 15:41
 */
public class HanNuoTa {
    private int i=1;

    /**
     *
     * @param n  ��1�ϵķ�����
     * @param from
     * @param dependOn
     * @param to
     */
    public void create(int n,String from,String dependOn,String to){
        if(n==1){
            move(1,from,to);
        }else{
            create(n-1,from,to,dependOn);
            move(n,from,to);
            create(n-1,dependOn,from,to);
        }
    }

    private void move(int n, String from, String to) {
        System.out.println("��"+i+++"��,������"+from+"------>"+to);
    }


    public static void main(String[] args) {
        HanNuoTa hanNuoTa = new HanNuoTa();
        hanNuoTa.create(4,"A","B","C");
    }
}
