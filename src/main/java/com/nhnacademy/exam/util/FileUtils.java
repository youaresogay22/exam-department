package com.nhnacademy.exam.util;

import com.nhnacademy.exam.exception.ReadTextFileIoException;
import lombok.extern.slf4j.Slf4j;
import java.io.*;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 15/05/2023
 */

@Slf4j
public final class FileUtils {
    private FileUtils(){
        throw new IllegalStateException("Utility class!");
    }

    public static String getTextFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (
                InputStream inputStream = new FileInputStream(file);
                Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {

            char[] buf = new char[1024];
            int len = 0;
            while ((len = reader.read(buf)) > 0) {
                sb.append(buf, 0, len);
            }
        }catch (Exception e) {
            throw new ReadTextFileIoException(file.getPath());
        }
        return sb.toString();
    }
}
