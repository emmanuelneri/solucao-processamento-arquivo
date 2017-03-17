package br.com.emmanuelneri.app.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public final class FileUtils {

    private static final String XML = "xml";

    private FileUtils() {}

    public static Collection<File> getFilesInDirectory(final File directory) throws IllegalArgumentException {
        if (directory == null) {
            throw new IllegalArgumentException("directory is mandatory");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("directory is not a directory");
        }

        return org.apache.commons.io.FileUtils.listFiles(directory, new String[]{XML}, false);
    }

    public static void move(File arquivoAtual, String pathDestino) throws IOException {
        org.apache.commons.io.FileUtils.moveFile(arquivoAtual, new File(pathDestino));
    }
}
