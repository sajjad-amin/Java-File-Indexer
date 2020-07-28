package com.sajjadamin.fileindexer;

import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class FileIndexer {

    private final ArrayList<FileInfo> list = new ArrayList<>();

    public ArrayList<FileInfo> indexAll(String path, Boolean subDir, Boolean hidden, Filter filter) {
        try {
            File[] files;
            if (filter != null){
                files = new File(path).listFiles(filter);
            }  else {
                files = new File(path).listFiles();
            }
            if (files != null){
                for (File file : files){
                    if (file.isDirectory() && subDir){
                        if (hidden){
                            indexAll(file.toString(), subDir, hidden, filter);
                        } else {
                            if (!file.isHidden()){
                                indexAll(file.toString(), subDir, hidden, filter);
                            }
                        }
                    } else {
                        if (hidden && !file.isDirectory()){
                            list.add(new FileInfo(file));
                        } else {
                            if (!file.isHidden() && !file.isDirectory()){
                                list.add(new FileInfo(file));
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            return list;
        }
        return list;
    }

    public ArrayList<FileInfo> search(String path, String keyword){
        try{
            File[] files = new File(path).listFiles();
            if (files != null){
                for (File file : files){
                    if (file.isDirectory()){
                        search(file.toString(),keyword);
                    } else {
                        if (file.toString().toLowerCase().contains(keyword.toLowerCase())){
                            list.add(new FileInfo(file));
                        }
                    }
                }
            }
        } catch (Exception e){
            return list;
        }
        return list;
    }

    public static class Filter implements FileFilter {
        private final String[] exts;
        public Filter(String[] extensions) {
            this.exts = extensions;
        }
        @Override
        public boolean accept(File file) {
            for (String ext : exts){
                if (file.isDirectory()){
                    return true;
                } else {
                    if (file.getName().toLowerCase().endsWith(ext)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class FileInfo {
        private final File file;
        private final String absolutePath;
        private final String fileName;
        private final long fileSize;
        public FileInfo(File file) {
            this.file = file;
            this.absolutePath = file.getAbsolutePath();
            this.fileSize = file.length();
            this.fileName = file.getName();
        }
        public File getFile(){
            return file;
        }
        public String getAbsolutePath() {
            return absolutePath;
        }
        public String getName(){
            return fileName;
        }
        public long getSize() {
            return fileSize;
        }
        public String getSizeInKB(){
            return new DecimalFormat("##.##").format((double) fileSize / 1024) + " KB";
        }
        public String getSizeInMB(){
            return new DecimalFormat("##.##").format(((double) fileSize / 1024) / 1024) + " MB";
        }
        public String getSimpleSize(){
            if (getSize() >= 1024 && getSize() < 1048576){
                return getSizeInKB();
            } else if(getSize() >= 1048576){
                return getSizeInMB();
            } else {
                return getSize() + " Bytes";
            }
        }
    }
}