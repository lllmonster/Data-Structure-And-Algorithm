// Write inputStream to File
import org.apache.commons.io.FileUtils;

private void wirteInputStreamToFile(String localFilePath) {
    InputStream fileInputStream;
    FileUtils.copyInputStreamToFile(fileInputStream, new File(localFilePath));
}

// Write File to outputStream
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
private ServletOutputStream writeFileToOutputStream(String sourceFile) {
    File file = new File(sourceFile);
    FileInputStream inStream = new FileInputStream(file);
    ServletOutputStream outStream = response.getOutputStream();
    byte[] buffer = new byte[4096];
    int bytesRead = -1;
    while (bytesRead = inStream.read(buffer) != -1) {
        outStream.write(buffer, 0, bytesRead);
    }

    inStream.close();
    outStream.close();
    response.flushBuffer();
}