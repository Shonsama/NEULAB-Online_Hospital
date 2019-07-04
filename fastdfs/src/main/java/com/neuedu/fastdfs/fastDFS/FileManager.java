package com.neuedu.fastdfs.fastDFS;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import java.io.File;


public class FileManager implements FileManagerConfig {

    private static final long serialVersionUID = 1L;
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;

    static {
        try {
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
            System.out.println("classpath: "+classPath);

            String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
            System.out.println("fdfsClientConfigFilePath: "+ fdfsClientConfigFilePath);
            ClientGlobal.init("/root/"+CLIENT_CONFIG_FILE);
//            ClientGlobal.init(fdfsClientConfigFilePath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <strong>方法概要： 文件上传</strong> <br>
     * <strong>创建时间： 2016-9-26 上午10:26:11</strong> <br>
     *
     * @param file
     *            file
     * @return fileAbsolutePath
     * @author Wang Liang
     */
    public static String upload(FastDFSFile file,NameValuePair[] valuePairs) {
        System.out.println("upload");
        System.out.println(file.getContent());
        System.out.println(file.getExt());
        for(int i = 0 ;i<valuePairs.length;i++){
            System.out.println(valuePairs[i]);
        }
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getContent(),file.getExt(), valuePairs);
            System.out.println(uploadResults.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(uploadResults.length);
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1] ;

        String fileAbsolutePath = PROTOCOL
                + TRACKER_NGNIX_ADDR
                //+ trackerServer.getInetSocketAddress().getHostName()
                //+ SEPARATOR + TRACKER_NGNIX_PORT
                + SEPARATOR + groupName
                + SEPARATOR + remoteFileName;
        System.out.println(fileAbsolutePath);
        return fileAbsolutePath;
    }
}