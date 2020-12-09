package test.task.TestForInterview.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.TestForInterview.model.FilesInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilesDataServiceImpl implements FilesDataService {

    @Autowired
    private FilesInfo filesInfo;

    @Override
    public FilesInfo getFilesInfo(String path) {

        List<Path> arrayOfDocs = getArrayOfDocs(path);
        filesInfo.setAmountOfDocuments(arrayOfDocs.size());

        long amountOfPages = 0;
        try {
            amountOfPages = countAmountOfPages(arrayOfDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filesInfo.setAmountOfPages(amountOfPages);
        return filesInfo;
    }

    /**Method gather all documents into List.
     *
     * @param pathStr
     * @return
     */
     private List<Path> getArrayOfDocs(String pathStr) {
        Path path = Paths.get(pathStr);

        List<Path> files = new ArrayList<>();
        try {
            files = Files.find(path, 5, (p, a) -> a.isRegularFile()
                    && (p.getFileName().toString().endsWith(".docx"))
                    || p.getFileName().toString().endsWith(".pdf"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }


    /** Counting pages in all documents.
     * Method can be modified by new formats of documents.
     * @param arrayOfDocs
     * @return amount of pages in all documents in directory.
     * @throws IOException
     */
    private long countAmountOfPages(List<Path> arrayOfDocs) throws IOException {

        long amountOfPages = 0;
        for (Path path : arrayOfDocs) {
            String lowerPath = path.toString().toLowerCase();

            if (lowerPath.endsWith(".docx")) {
                XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(lowerPath));
                amountOfPages += docx.getProperties().getExtendedProperties().getPages();
                docx.close();
            } else if (lowerPath.endsWith(".pdf")) {
                PDDocument doc = PDDocument.load(new File(path.toString()));
                amountOfPages += doc.getNumberOfPages();
                doc.close();
            }
        }
        return amountOfPages;
    }
}
