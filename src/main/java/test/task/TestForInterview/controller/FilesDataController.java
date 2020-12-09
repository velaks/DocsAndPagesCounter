package test.task.TestForInterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.task.TestForInterview.model.FilesInfo;
import test.task.TestForInterview.service.FilesDataService;

@RequestMapping
@RestController
public class FilesDataController {

    @Autowired
    private FilesDataService filesDataService;

    /**
     * Returns number of document and pages,
     * receives param with path.
     * @param path - directory of root folder.
     * @return object with data.
     */
    @GetMapping("/getInfo")
    public FilesInfo getFilesInfo(@RequestParam String path) {

        return filesDataService.getFilesInfo(path);
    }
}
