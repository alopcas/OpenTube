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
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author lope115
 */
public class DownloadManager extends SwingWorker {

    private String downloadFolderPath;
    private String tmpFolderPath;
    private List<Download> downloads;
    private final Main mainFrame;

    public DownloadManager(Main frame, String tmpFolderPath, String downloadFolderPath) {
        this.downloadFolderPath = downloadFolderPath;
        this.tmpFolderPath = tmpFolderPath;
        this.downloads = new ArrayList<>();
        this.mainFrame = frame;
    }

    public void addDownloadToQueue(String mediaURL, String mediaFileName, String endFormat) throws MalformedURLException {
        Download download = new Download(mediaURL, downloadFolderPath + mediaFileName, endFormat);
        downloads.add(download);
    }

    @Override
    protected Object doInBackground() throws Exception {
        for (Download e : downloads) {
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
            mainFrame.changeStatus("Converting");
            downloads.remove(e);
        }
        return null;
    }
    
    @Override
    protected void done() {
        //When the job is done, switch the status
        mainFrame.changeStatus("Ready");
    }

}
