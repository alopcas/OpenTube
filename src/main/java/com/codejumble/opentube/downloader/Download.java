/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codejumble.opentube.downloader;

import com.github.axet.vget.VGet;
import com.github.axet.wget.info.DownloadInfo;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.SwingWorker;

/**
 *
 * @author lope115
 */
public class Download extends SwingWorker {

    private final VGet v;
    private DownloadInfo downloadInfo;
    private File targetFile;
    private int progress;
    private String endFormat;

    public Download(String videoURL, String path, String targetFileName, String endFormat) throws MalformedURLException {
        targetFile = new File(path + File.separator + targetFileName +".mp4");
        this.endFormat = endFormat;
        v = new VGet(new URL(videoURL), new File(path));
        v.setTarget(targetFile);
        progress = 0;
        downloadInfo = v.getVideo().getInfo();
    }

    public VGet getV() {
        return v;
    }

    public File getTargetFile() {
        return targetFile;
    }

    public String getEndFormat() {
        return endFormat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.targetFile != null ? this.targetFile.hashCode() : 0);
        hash = 41 * hash + (this.endFormat != null ? this.endFormat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Download other = (Download) obj;
        if (this.targetFile != other.targetFile && (this.targetFile == null || !this.targetFile.equals(other.targetFile))) {
            return false;
        }
        if ((this.endFormat == null) ? (other.endFormat != null) : !this.endFormat.equals(other.endFormat)) {
            return false;
        }
        return true;
    }

    @Override
    protected Object doInBackground() {
        setProgress(progress);
        v.download();
        return null;
    }

    @Override
    protected void done() {
        setProgress(100);
    }

    public void refreshProgress() {
        downloadInfo = v.getVideo().getInfo();
        if (downloadInfo != null && targetFile.exists()) {
            double progressWithDecimals = ((double) targetFile.length() / downloadInfo.getLength());
            progress = (int) Math.round(progressWithDecimals * 100); //Percentage
            setProgress(Math.min(progress, 99));
        }
    }

}
