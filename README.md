# Java File Indexer
A simple useful library for indexing files in java project
### Uses
Download [FileIndexer.jar](https://github.com/sajjad-amin/Java-File-Indexer/raw/master/out/artifacts/FileIndexer.jar) and add into your project.

The base class is **FileIndexer** and it has two static inner class - **FileIndexer.FileInfo**, **FileIndexer.Filter** and two method **indexAll()**, **search()**.
Both methods are return an ArrayList which contains FileIndexer.FileInfo class.

**search()** accepts two parameters -
1. String path
    > location of directory where you want to search
2. String keyword
    > search() method will match file and directory names containing this keyword and return only matched items

**indexAll()** accepts four parameters -
1. String
    > Location of directory where you want to start indexing
2. Boolian
    > If it true, then it will access all sub directories and their sub directories. Otherwise it will ignore sub directories.
3. Boolean
    > If it true, then it will accept everything. Otherwise it will ignore hidden directories and files.
4. FileIndexer.Filter
    > If you want to accept only spacific types of file like- mp4, mp3, dox etc. you can create a Filter and pass on it. Otherwise you can leav it by passing **null**. for example -
    
    ```
    new FileIndexer.Filter(new String[]{"mp4","mp3","dox"})
    ```

In FileIndexer.FileInfo class, there are 7 methods -
1. getFile()
    - Type : File
    - Returns File object
2. getAbsolutePath()
    - Type : String
    - Returns absolute path of file
3. getName()
    - Type : String
    - Returns file name
4. getSize()
    - Type : Long
    - Returns total byte of file
5. getSizeInKB()
    - Type : String
    - Returns file size in KB
6. getSizeInMB()
    - Type : String
    - Returns file size in MB
7. getSimpleSize()
    - Type : String
    - Returns file size according to Byte/KB/MB

### Example
```
ArrayList<FileIndexer.FileInfo> list;

FileIndexer indexer = new FileIndexer();

String path = "/home/sayem/Videos";

FileIndexer.Filter filter = new FileIndexer.Filter(new String[]{"mp4","mkv"});

list = indexer.indexAll(path,true,true,filter);

for (FileIndexer.FileInfo info : list){
    System.out.println("Path : "+info.getAbsolutePath());
    System.out.println("Size : "+info.getSimpleSize());
}

list = indexer.search(path, "movies");

for (FileIndexer.FileInfo info : list){
    System.out.println("Path : "+info.getAbsolutePath());
    System.out.println("Size : "+info.getSimpleSize());
}
```
