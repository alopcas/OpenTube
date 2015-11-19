/**
 * *****************************************************************************
 * Copyright (c) 2015 CodeJumble.com. All rights reserved.
 *
 * This file is part of OpenTube - www.codejumble.com
 *
 *  Foobar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  Foobar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************************************
 */
package com.codejumble.opentube.downloader;

import com.github.axet.vget.VGet;
import com.github.axet.wget.info.DownloadInfo;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implementing single downloads logic. Each download contains the core
 * information such as temporal file path, file format, or current progress.
 *
 * @author alopcas
 */
public class Download extends SwingWorker {
    
    private final VGet v;
    private DownloadInfo downloadInfo;
    private File targetFile;
    private int progress;
    private String endFormat;

    //Logging 
    Logger logger = LoggerFactory.getLogger(Download.class);

    /**
     * Initilizes a download with the given fields.
     *
     * @param videoURL URL of the video on the internet
     * @param path Folder where the temporal file will be alocated
     * @param targetFileName Name of the end file
     * @param endFormat Output format of the program
     * @throws MalformedURLException when the given URL does not follow the
     * standards (https://url.spec.whatwg.org/)
     */
    public Download(String videoURL, String path, String targetFileName, String endFormat) throws MalformedURLException {
        targetFile = new File(path + File.separator + targetFileName + endFormat);
        this.endFormat = endFormat;
        v = new VGet(new URL(videoURL), new File(path));
        v.setTarget(targetFile);
        progress = 0;
        downloadInfo = v.getVideo().getInfo();
        logger.info("New download created. url = {}, file={}", videoURL, targetFileName + endFormat);
    }

    /**
     * Returns the VGet of the current download. This element contains very
     * important information
     *
     * @return VGet object of the download
     */
    public VGet getV() {
        return v;
    }

    /**
     * Returns the temporal file
     *
     * @return temporal file
     */
    public File getTargetFile() {
        return targetFile;
    }

    /**
     * Returns the output format desired by the user
     *
     * @return output format
     */
    public String getEndFormat() {
        return endFormat;
    }

    /**
     * Returns the hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.targetFile != null ? this.targetFile.hashCode() : 0);
        hash = 41 * hash + (this.endFormat != null ? this.endFormat.hashCode() : 0);
        return hash;
    }

    /**
     * Says if current download is equal to the one inputed
     *
     * @param obj other download object
     * @return true if equal
     */
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

    /**
     * Runs the download in the background.
     *
     * @return null
     */
    @Override
    protected Object doInBackground() {
        logger.info("Starting download. Output file={}", this.targetFile.getName());
        setProgress(progress);
        try {
            v.download();
        } catch (Exception e) {
            logger.error("Error with message {}", e.getMessage());
        }
        return null;
    }

    /**
     * Sets the progress to 100 and finishes.
     */
    @Override
    protected void done() {
        logger.info("Download concluded successfully");
        setProgress(100);
    }

    /**
     * Updates the progress value of the download using temporal file size and
     * download file size.
     */
    public void refreshProgress() {
        downloadInfo = v.getVideo().getInfo();
        if (downloadInfo != null && targetFile.exists()) {
            double progressWithDecimals = ((double) targetFile.length() / downloadInfo.getLength());
            progress = (int) Math.round(progressWithDecimals * 100); //Percentage
            setProgress(Math.min(progress, 99));
        }
    }
    
}
