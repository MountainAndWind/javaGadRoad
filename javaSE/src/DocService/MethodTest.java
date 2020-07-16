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
            //根据Eclipse生成的Web Service客户端程序，取得接口
            DocServicePortType service = new DocServiceLocator().getDocServiceHttpPort(new URL("http://localhost:8081//services/DocService"));
            //登陆
            String session = service.login("sysadmin", "1", 0, "127.0.0.1");
            //取得有权限访问的文档数
            int count = service.getDocCount(session);

            //取得有权限访问的文档列表，第1页，每页10条记录
            DocInfo[] docs1 = service.getList(session,1,10);
            //取得有权限访问的文档列表，第2页，每页10条记录
            DocInfo[] docs2 = service.getList(session,2,10);
            //循环打印取得的文档的ID、标题、主目录ID、主目录名称、分目录ID、分目录名称、
            //子目录ID、子目录名称、文档创建人ID、文档创建人名称、创建日期、创建时间
            for(int i=0;i<docs1.length;i++)
                System.out.println(docs1[i].getId()+"|"+docs1[i].getDocSubject()+"|"+
                        docs1[i].getMaincategory()+"|"+docs1[i].getMaincategoryStr()+"|"+
                        docs1[i].getSubcategory()+"|"+docs1[i].getSubcategoryStr()+"|"+
                        docs1[i].getSeccategory()+"|"+docs1[i].getSeccategoryStr()+"|"+
                        docs1[i].getDoccreaterid()+"|"+docs1[i].getDoccreatername()+"|"+
                        docs1[i].getDoccreatedate()+"|"+docs1[i].getDoccreatetime());

            //根据ID取得文档内容
            DocInfo doc = service.getDoc(13095,session);
            //取得该文档的第一个附件
            DocAttachment da = doc.getAttachments()[0];
            //得到附件内容
            byte[] content = Base64.decode(da.getFilecontent());
            //得到附件在服务器上的绝对路径
            String filepath = da.getFilerealpath();

            //将附件内容转存至D:\
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

            //根据绝对路径转存附件(仅限与ecology系统在同一服务器上)
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


            //上传附件，更新文档
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

            //上传附件，创建文档
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
