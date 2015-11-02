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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lope115
 */
public class DownloadManager {

    private String path;
    private List<Download> downloads;

    public DownloadManager() {
        this.path = "C:" + File.separator + "Users" + File.separator + "lope115" + File.separator + "Desktop" + File.separator + ""; //This must be retrieved from conf
        this.downloads = new ArrayList<>();
    }

    public void addDownloadToQueue(String videoURL, String videoFileName) throws MalformedURLException {
        Download download = new Download(videoURL, path + "asdada.mp4");
        downloads.add(download);
    }

    public void download(Main mainFrame) {
        downloads.stream().forEach((e) -> {
            e.addPropertyChangeListener(mainFrame);
            e.execute();
            while(e.getProgress()<100){
                e.refreshProgress();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ex) {
                    //TODO
                }
            }
        });
    }
}
