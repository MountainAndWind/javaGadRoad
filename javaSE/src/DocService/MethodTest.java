package DocService;


import java.io.*;
import java.net.URL;
import java.util.zip.ZipInputStream;
import org.apache.axis.encoding.Base64;
import weaver.docs.webservices.DocAttachment;
import weaver.docs.webservices.DocInfo;

/**
 * @description:
 * @author: slfang
 * @time: 2020/6/28 13:52
 */
public class MethodTest {
    public static void main(String[] args) {
        try {
            //����Eclipse���ɵ�Web Service�ͻ��˳���ȡ�ýӿ�
            DocServicePortType service = new DocServiceLocator().getDocServiceHttpPort(new URL("http://localhost:8081//services/DocService"));
            //��½
            String session = service.login("sysadmin", "1", 0, "127.0.0.1");
            //ȡ����Ȩ�޷��ʵ��ĵ���
            int count = service.getDocCount(session);

            //ȡ����Ȩ�޷��ʵ��ĵ��б���1ҳ��ÿҳ10����¼
            DocInfo[] docs1 = service.getList(session,1,10);
            //ȡ����Ȩ�޷��ʵ��ĵ��б���2ҳ��ÿҳ10����¼
            DocInfo[] docs2 = service.getList(session,2,10);
            //ѭ����ӡȡ�õ��ĵ���ID�����⡢��Ŀ¼ID����Ŀ¼���ơ���Ŀ¼ID����Ŀ¼���ơ�
            //��Ŀ¼ID����Ŀ¼���ơ��ĵ�������ID���ĵ����������ơ��������ڡ�����ʱ��
            for(int i=0;i<docs1.length;i++)
                System.out.println(docs1[i].getId()+"|"+docs1[i].getDocSubject()+"|"+
                        docs1[i].getMaincategory()+"|"+docs1[i].getMaincategoryStr()+"|"+
                        docs1[i].getSubcategory()+"|"+docs1[i].getSubcategoryStr()+"|"+
                        docs1[i].getSeccategory()+"|"+docs1[i].getSeccategoryStr()+"|"+
                        docs1[i].getDoccreaterid()+"|"+docs1[i].getDoccreatername()+"|"+
                        docs1[i].getDoccreatedate()+"|"+docs1[i].getDoccreatetime());

            //����IDȡ���ĵ�����
            DocInfo doc = service.getDoc(13095,session);
            //ȡ�ø��ĵ��ĵ�һ������
            DocAttachment da = doc.getAttachments()[0];
            //�õ���������
            byte[] content = Base64.decode(da.getFilecontent());
            //�õ������ڷ������ϵľ���·��
            String filepath = da.getFilerealpath();

            //����������ת����D:\
            File file = new File("d:\\"+da.getFilename());
            try {
                int byteread;
                byte data[] = new byte[1024];
                InputStream imagefile = null;
                if(da.getIszip()==1){
                    ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(content));
                    if (zin.getNextEntry() != null)
                        imagefile = new BufferedInputStream(zin);
                } else {
                    imagefile = new ByteArrayInputStream(content);
                }
                OutputStream out = new FileOutputStream(file);
                while ((byteread = imagefile.read(data)) != -1) {
                    out.write(data, 0, byteread);
                    out.flush();
                }
                imagefile.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //���ݾ���·��ת�渽��(������ecologyϵͳ��ͬһ��������)
            try {
                int byteread;
                byte data[] = new byte[1024];
                InputStream imagefile = null;
                if(da.getIszip()==1){
                    ZipInputStream zin = new ZipInputStream(new FileInputStream(new File(da.getFilerealpath())));
                    if (zin.getNextEntry() != null)
                        imagefile = new BufferedInputStream(zin);
                } else {
                    imagefile = new FileInputStream(new File(da.getFilerealpath()));
                }

                OutputStream out = new FileOutputStream(file);
                while ((byteread = imagefile.read(data)) != -1) {
                    out.write(data, 0, byteread);
                    out.flush();
                }
                imagefile.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            //�ϴ������������ĵ�
            content = null;
            try {
                int byteread;
                byte data[] = new byte[1024];
                InputStream input = new FileInputStream(new File("d:\\123.pdf"));

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while ((byteread = input.read(data)) != -1) {
                    out.write(data, 0, byteread);
                    out.flush();
                }
                content = out.toByteArray();
                input.close();
                out.close();
            } catch(Exception e){
                e.printStackTrace();
            }
            da.setDocid(doc.getId());
            da.setImagefileid(0);
            da.setFilecontent(Base64.encode(content));
            da.setIszip(1);

            doc.setDocSubject("test");
            doc.setDoccontent("content");
            doc.setAttachments(new DocAttachment[]{da});
            service.updateDoc(doc, session);

            //�ϴ������������ĵ�
            content = null;
            try {
                int byteread;
                byte data[] = new byte[1024];
                InputStream input = new FileInputStream(new File("d:\\123.pdf"));

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while ((byteread = input.read(data)) != -1) {
                    out.write(data, 0, byteread);
                    out.flush();
                }
                content = out.toByteArray();
                input.close();
                out.close();
            } catch(Exception e){
                e.printStackTrace();
            }

            da.setDocid(0);
            da.setImagefileid(0);
            da.setFilecontent(Base64.encode(content));
            da.setFilerealpath("d:\\123.pdf");
            da.setIszip(1);

            doc.setId(0);
            doc.setDocSubject("test pdf1");
            doc.setDoccontent("abcefghijk");
            doc.setAttachments(new DocAttachment[]{da});
            service.createDoc(doc, session);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
