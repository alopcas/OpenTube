/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codejumble.opentube.downloader;

import com.codejumble.opentube.Main;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author lope115
 */
public class DownloadManager extends SwingWorker {

    private File downloadFolder;
    private File tmpFolder;
    private List<Download> downloads;
    private final Main mainFrame;

    public DownloadManager(Main frame, String tmpFolderPath, String downloadFolderPath) {
        this.downloadFolder = new File(downloadFolderPath);
        this.tmpFolder = new File(tmpFolderPath);
        this.downloads = new ArrayList<Download>();
        this.mainFrame = frame;
    }

    public void addDownloadToQueue(String mediaURL, String mediaFileName, String endFormat) throws MalformedURLException {
        Download download = new Download(mediaURL, tmpFolder.getAbsolutePath(), mediaFileName, endFormat);
        downloads.add(download);
    }

    @Override
    protected Object doInBackground() throws Exception {
        try{
        for (int i = 0 ; i<downloads.size() ; i++) {
            Download e = downloads.get(i);
            mainFrame.changeStatus("Downloading");
            e.addPropertyChangeListener(mainFrame);
            e.execute();
            while (e.getProgress() < 100) {
                e.refreshProgress();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ex) {
                    //TODO
                }
            }
//            if (!e.getEndFormat().equals("mp4")) {
//                //if conversion needs to be performed
//                mainFrame.changeStatus("Converting");
//                String tmp = e.getTargetFile().getAbsolutePath();
//                String tmp2 = tmpFolder.getAbsolutePath() + File.separator + e.getTargetFile().getName().replace("mp4", e.getEndFormat());
//
//                IMediaReader reader = ToolFactory.makeReader(tmp);
//                reader.addListener(ToolFactory.makeWriter(tmp2, reader));
//                while (reader.readPacket() == null)
//   ;
//            }
            //Move downloaded file to final destination
            mainFrame.changeStatus("Locating");
            File currentDownload = e.getTargetFile();
            FileUtils.copyFile(currentDownload, new File(downloadFolder + File.separator + currentDownload.getName()));

            //Clear tmp folder
            FileUtils.cleanDirectory(tmpFolder);
        }}
        catch(Exception e){
            
        }
        downloads.clear();
        return null;
    }

    @Override
    protected void done() {
        //When the job is done, switch the status
        mainFrame.changeStatus("Ready");
    }

}
